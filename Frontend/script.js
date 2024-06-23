let listOfItems = [];

async function createItem(itemData) {
    try {
        const response = await axios.post('http://localhost:8080/api/items/createItem', itemData, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.data === "Item created successfully") {
            return true;
        } else if (response.data === "This Item already exists") {
            return false;
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

// Function to create a box element with the specified name and category
function createBox(name, category) {
    const bigWrapper = document.createElement("div");
    bigWrapper.classList.add("bigWrapper");
    document.body.appendChild(bigWrapper);

    const wrapper = document.createElement("div");
    wrapper.classList.add("wrapper");
    bigWrapper.appendChild(wrapper);

    const titleWrapper = document.createElement("div");
    titleWrapper.classList.add("title");
    titleWrapper.textContent = category;
    wrapper.appendChild(titleWrapper);

    const bigBox = document.createElement("div");
    bigBox.classList.add("bigBox");
    wrapper.appendChild(bigBox);

    const box = document.createElement("div");
    box.classList.add("box");
    box.textContent = name;
    box.addEventListener("click", function() {
        getIdFromBox(name, category);
        this.classList.toggle("boxed_clicked");
    });

    bigBox.appendChild(box);
}

// Function to handle the click event of the create box button
document.getElementById("createBoxBtn").addEventListener("click", async function() {
    // Retrieve the value of the input fields
    const inputName = document.getElementById("name").value;
    const inputCategory = document.getElementById("category").value;

    if (inputName === '' || inputCategory === '') {
        alert("Please, enter something");
        return;
    }

    const data = { name: inputName, category: inputCategory };

    const result = await createItem(data);
    if (result) {
        createBox(inputName, inputCategory);
        alert("Item created successfully");
        window.location.reload();
    } else {
        alert("Item already exists");
    }
});

// Function to get IDs from the server
async function getIdFromBox(boxName, boxCategory) {
    try {
        const response = await axios.get(`http://localhost:8080/api/items/getIds`, {
            params: {
                nameOfBox: boxName,
                categoryOfBox: boxCategory
            }
        });
        listOfItems.push(response.data);
    } catch (error) {
        alert("An error occurred while fetching IDs");
        console.error('Error fetching IDs:', error);
    }
}

// Function to randomize items
async function randomizeTheseItems(listItems) {
    try {
        const response = await axios.get('http://localhost:8080/api/items/randomize', {
            params: {
                ids: listItems.join(',')
            }
        });
        displayOverlay(response.data);
    } catch (error) {
        console.error('Error randomizing items:', error);
    }
}

// Function to display overlay with randomized text
function displayOverlay(randomizedText) {
    const overlay = document.createElement('div');
    overlay.classList.add('overlay');

    const bigText = document.createElement('h1');
    bigText.textContent = randomizedText;
    overlay.appendChild(bigText);

    document.body.appendChild(overlay);

    setTimeout(() => {
        window.location.reload();
    }, 10000);
}

// Event listener for the randomize button
document.getElementById("randomizeBtn").addEventListener("click", function() {
    randomizeTheseItems(listOfItems);
});

// Function to display all items
async function displayAllItems() {
    try {
        const response = await axios.get('http://localhost:8080/api/items/allItems');
        const categoryWrappers = {};

        response.data.forEach(item => {
            let wrapper = categoryWrappers[item.category];

            if (!wrapper) {
                wrapper = createCategoryWrapper(item.category);
                categoryWrappers[item.category] = wrapper;
            }

            createBoxElement(item.name, wrapper);
        });
    } catch (error) {
        console.error('Error fetching items:', error);
    }
}

// Function to create a category wrapper
function createCategoryWrapper(category) {
    const bigWrapper = document.createElement("div");
    bigWrapper.classList.add("bigWrapper");
    document.body.appendChild(bigWrapper);

    const wrapper = document.createElement("div");
    wrapper.classList.add("wrapper");
    bigWrapper.appendChild(wrapper);

    const titleWrapper = document.createElement("div");
    titleWrapper.classList.add("title");
    titleWrapper.textContent = category;
    wrapper.appendChild(titleWrapper);

    const bigBox = document.createElement("div");
    bigBox.classList.add("bigBox");
    wrapper.appendChild(bigBox);

    return wrapper;
}

// Function to create a box element and append to wrapper
function createBoxElement(name, wrapper) {
    const bigBox = wrapper.querySelector(".bigBox");

    const box = document.createElement("div");
    box.classList.add("box");
    box.textContent = name;
    box.addEventListener("click", function() {
        getIdFromBox(name, wrapper.querySelector('.title').textContent);
        this.classList.toggle("boxed_clicked");
    });

    bigBox.appendChild(box);
}

// Initialize display of all items
displayAllItems();
