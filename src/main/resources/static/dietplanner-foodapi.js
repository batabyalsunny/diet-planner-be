/**
 * FOOD NUTRITION DATA SEARCH ALGORITHM
 * Author : Subhankar Das
 * Function makes GET call to DietPlanner API i.e search food with name.
 * If response is null call Nutritionix API food search and store the response.
 * @param {string} foodName Name of the food item to search
 */
const foodItemsUrl = 'http://localhost:8081/api/foods';
const idealFoodItemsUrl = 'http://localhost:8081/api/foods/ideal';

function searchFoodItem(foodName, handler) {
    foodName = foodName.toUpperCase();
    console.log('INFO: Searching for food item: ' + foodName);

    // Search in local database
    $.ajax({
        url: foodItemsUrl,
        type: "GET",
        dataType: "json",
        data: {
            name: foodName
        },
        success: function (result) {
            console.log('INFO: Found ' + foodName + ' in database!');
            finalizeFoodSearch('success', result);
        },
        error: function () {
            console.log('ERROR: Not found! Calling Nutritionix.');
            nutritionixApiFoodSearch(foodName);
        }
    });
}

/**
 * Saves a newly found food item to database. 
 * Food item contains all nutrition data from Nutritionix database.
 * @param {object} foodItem Food item object
 */
function addFoodItem(foodItem) {
    console.log('INFO: Saving food item...');

    // POST call to save food item
    $.ajax({
        url: foodItemsUrl,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Access-Control-Allow-Origin': '*'
        },
        type: "POST",
        dataType: "json",
        data: {
            // Pass nutrient values as request params
            name: foodItem.foodName,
            quantity: foodItem.serving,
            unit: foodItem.servingUnit,
            weight: foodItem.servingWeight,
            thumbUrl: foodItem.thumbUrl,
            imageUrl: foodItem.imageUrl,
            calories: foodItem.calories,
            fat: foodItem.fat,
            satFat: foodItem.satFat,
            cholestrol: foodItem.cholesterol,
            sodium: foodItem.sodium,
            carbohydrates: foodItem.carbohydrate,
            protein: foodItem.protein,
            potassium: foodItem.potassium
        },
        success: function (result) {
            console.log("INFO: Food item saved successfully!");
            // Finalize food search
            finalizeFoodSearch('success', result);
        },
        error: function (result) {
            console.log("ERROR: Failed to saved food item!");
        }
    });
}

/**
 * Finalizes the food search algorithm and performs all closing tasks.
 * @param {object} response Response object
 */
function finalizeFoodSearch(status, response) {
    // Error occured
    if (status == 'error') {
        console.log('ERROR: Unable to find searched food item!');
    }
    // Food search successful
    else {
        console.log('SUCCESS: Finalized food search!');
        // console.log('RESULT: ' + JSON.stringify(response));
        
        // Added food item to database, now add it to ideal diet plan
        addFoodItemToIdealFoodItems(foodCourseId, response.id);
    }
}

/**
 * Function calls Nutritionix API via RapidAPI interface.
 * @param {string} foodName Name of the food item to search
 */
var foodItemsList; // Global list of foods
function nutritionixApiFoodSearch(foodName) {
    var appId = 'c9661217'; // Nutrinix app ID
    var appKey = '1b456d0ea600af1cfa43ec771747c3bc'; // Nutrinix app key

    var rapidApiKey = 'a2744339-227e-4fda-b8f6-1d974b13d39b'; // RapidAPI key
    var rapidAppName = 'diet_planner'; // Rapid app name
    var rapidApiName = 'Nutritionix'; // Used API name
    var rapidApiMethodName = 'getFoodsNutrients' // API Method to be called

    // Create object to call Rapid API
    const rapid = new RapidAPI(rapidAppName, rapidApiKey);

    // GET call Nutritionix API using RapidAPI
    rapid.call(rapidApiName, rapidApiMethodName, {
        'applicationId': appId,
        'foodDescription': foodName, // Pass the search parameter
        'applicationSecret': appKey
    }).on('success', function (payload) {
        // API call successful
        var data = JSON.parse(JSON.stringify(payload)); // Parse response
        // Select first element from response i.e list of common foods
        var foodItems = data[0];
        var length = foodItems.foods.length; // Length of foods list

        foodItemsList = foodItems; // Update foods list i.e global

        // Loop foods list and generate suggestions
        suggestions = '';
        for (var i = 0; i < length; i++) {
            suggestions += 'ID: ' + i + ' -     ' + foodItems.foods[i].food_name.toUpperCase() +
                '     <a href=' + foodItems.foods[i].photo.thumb + '>THUMB</a><br>';
        }

        // Show suggestions in HTML
        $("#suggestions").html(suggestions);

        console.log('INFO: Found ' + foodName + ' in Nutritionix database!');
    }).on('error', function (payload) {
        // Error caused! Now finalize food search
        finalizeFoodSearch('error', null); // No such food item found
    });
}

/**
 * Returns food item object from Nutritionix selected by user.
 * @param {object} foodItem Food item to be saved
 */
var foodCourseId;
function addSelectedFoodItem(selectedIndex, foodCourse) {
    // Get selected food item from list
    var foodItem = foodItemsList.foods[selectedIndex];

    // Update the food course ID
    foodCourseId = foodCourse;

    // Extract required data from the response
    var response = {
        status: 'success',
        foodName: foodItem.food_name,
        serving: foodItem.serving_qty,
        servingUnit: foodItem.serving_unit,
        servingWeight: foodItem.serving_weight_grams,
        calories: foodItem.nf_calories,
        fat: foodItem.nf_total_fat,
        satFat: foodItem.nf_saturated_fat,
        cholesterol: foodItem.nf_cholesterol,
        sodium: foodItem.nf_sodium,
        carbohydrate: foodItem.nf_total_carbohydrate,
        protein: foodItem.nf_protein,
        potassium: foodItem.nf_potassium,
        thumbUrl: foodItem.photo.thumb,
        imageUrl: foodItem.photo.highres
    };

    // Got food item details now add it to local database
    addFoodItem(response);
}

function addFoodItemToIdealFoodItems(foodCourseId, foodItemId) {
    // Add food item to ideal food items list
    $.ajax({
        url: idealFoodItemsUrl,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Access-Control-Allow-Origin': '*'
        },
        type: "POST",
        dataType: "json",
        data: {
            // Pass request params
            course: foodCourseId,
            foodItemId: foodItemId
        },
        success: function (result) {
            console.log("INFO: Food item added to ideal foods list!");
        },
        error: function (result) {
            console.log("ERROR: Failed to saved food item to ideal foods list!");
        }
    });
}