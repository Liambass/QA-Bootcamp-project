"use strict";

const ADDR = "http://localhost";

// Divs
const RESULTS_DIV = document.querySelector("#results-div");
const FORM_DIV = document.querySelector("#form-div");

// Buttons
const CREATE_BTN = document.querySelector("#create-btn");

// Inputs
const TITLE = document.querySelector(".title-input");
const GENRE = document.querySelector(".genre-input");
const YEAR = document.querySelector(".year-input");
const DURATION = document.querySelector(".duration-input");

// Setup
const setup = () => {
    datepickerSetup();
    getAll();
}

// Get request
const getAll = () => {
    axios.get(`${ADDR}:${location.port}/films/readAll`)
        .then((resp) => {
            RESULTS_DIV.innerHTML = "";
            const RESULTS = resp.data;
            for (let result of RESULTS) {
                printResult(result);
            }
        }).catch((err) => console.error(err))
}

// Post request
const create = () => {
    // Validate the form
    if (!validateForm("create")) {
        return;
    }

    // Input values
    const TITLE_VALUE = TITLE.value;
    const GENRE_VALUE = GENRE.value;
    const YEAR_VALUE = YEAR.value;
    const DURATION_VALUE = DURATION.value;

    // New object to be created
    let obj = {
        "title": TITLE_VALUE,
        "genre": GENRE_VALUE,
        "year": YEAR_VALUE,
        "duration": DURATION_VALUE
    };

    // Request
    axios.post(`${ADDR}:${location.port}/films/create`, obj)
        .then((resp) => {
            statusMsg(true);
            getAll();
        }).catch((err) => {
            console.error(err);
            statusMsg(false);
        })
}

// Put request
const update = () => {
    // Validate the form
    if (!validateForm("edit")) {
        return;
    }

    const EDIT_FORM = document.forms["editForm"];

    // New object for update
    let obj = {
        "title": EDIT_FORM["title"].value,
        "genre": EDIT_FORM["genre"].value,
        "year": EDIT_FORM["year"].value,
        "duration": EDIT_FORM["duration"].value
    };

    // Request
    axios.put(`${ADDR}:${location.port}/films/update/${EDIT_FORM["entry-id"].value}`, obj)
        .then((resp) => {
            // statusMsg(true);
            $("#edit-modal").modal("hide");
            getAll();
        }).catch((err) => {
            console.error(err);
            // statusMsg(false);
        })
}

// Delete request
const del = (id) => {
    axios.delete(`${ADDR}:${location.port}/films/delete/${id}`)
        .then((resp) => {
            getAll();
        }).catch((err) => {
            alert(err);
        })
}

const statusMsg = (bool) => {
    CREATE_BTN.setAttribute("disabled", "true");
    if (bool) {
        const ALERT_SUC = document.createElement("div");
        ALERT_SUC.setAttribute("class", "alert alert-success");
        ALERT_SUC.innerHTML = "Creation Successful"
        FORM_DIV.appendChild(ALERT_SUC);
        setTimeout(() => {
            FORM_DIV.removeChild(document.querySelector(".alert-success"));
            CREATE_BTN.removeAttribute("disabled");
        }, 2000)
    } else {
        const ALERT_ERR = document.createElement("div");
        ALERT_ERR.setAttribute("class", "alert alert-danger");
        ALERT_ERR.innerHTML = "Creation Failed";
        FORM_DIV.appendChild(ALERT_ERR);
        setTimeout(() => {
            FORM_DIV.removeChild(document.querySelector(".alert-danger"));
            CREATE_BTN.removeAttribute("disabled");
        }, 2000);
    }
}

const printResult = (result) => {
    const ENTRY_DIV = document.createElement("div");
    ENTRY_DIV.setAttribute("class", "entry-div");

    const ENTRY = document.createElement("div");
    ENTRY.setAttribute("class", "entry");

    const VALUES = document.createElement("div");
    VALUES.setAttribute("class", "entry-values");
    VALUES.textContent = `${result.title} | ${result.genre} | ${result.year} | ${result.duration} minutes`;
    

    const EDIT = document.createElement("button");
    EDIT.type = "button";
    EDIT.textContent = "Edit";
    EDIT.id = `${result.id}`;
    EDIT.setAttribute("class", "btn btn-sm btn-primary edit-btn");
    EDIT.setAttribute("onClick", "openEdit(this.id)");

    const DEL = document.createElement("button");
    DEL.type = "button";
    DEL.textContent = "Delete";
    DEL.id = `${result.id}`;
    DEL.setAttribute("class", "btn btn-sm btn-danger del-btn");
    DEL.setAttribute("onClick", "del(this.id)");

    ENTRY.appendChild(VALUES);
    ENTRY_DIV.appendChild(ENTRY);
    ENTRY_DIV.appendChild(EDIT);
    ENTRY_DIV.appendChild(DEL);
    
    RESULTS_DIV.appendChild(ENTRY_DIV);
}

const validateForm = (type) => {
    const CREATE_FORM = document.forms[`${type}Form`];
    if (CREATE_FORM["title"].value == "" || CREATE_FORM["genre"].value == "" || CREATE_FORM["year"].value == "" || CREATE_FORM["duration"].value == "") {
        alert("All fields require a value!");
        return false;
    }
    return true;
}

const openEdit = (id) => {
    // Show modal and configure date field
    $("#edit-modal").modal("show");
    datepickerSetup();

    // Get the current values for selected entry
    axios.get(`${ADDR}:${location.port}/films/readOne/${id}`)
        .then((resp) => {
            const ENTRY = resp.data;
            // Populate modal form with current values
            const EDIT_FORM = document.forms["editForm"];
            EDIT_FORM["title"].value = ENTRY.title;
            EDIT_FORM["genre"].value = ENTRY.genre;
            EDIT_FORM["year"].value = ENTRY.year;
            EDIT_FORM["duration"].value = ENTRY.duration;
            EDIT_FORM["entry-id"].value = ENTRY.id;
        }).catch((err) => console.error(err))
}

const datepickerSetup = () => {
    // Sets to year only
    $(".year-input").datepicker({
        format: "yyyy",
        viewMode: "years",
        minViewMode: "years"
    });
}