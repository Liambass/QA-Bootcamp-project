"use strict";

const ADDR = "http://localhost";

// Divs
const RESULTS_DIV = document.querySelector("#results-div");
const FORM_DIV = document.querySelector("#form-div");
const BOOKINGS_DIV = document.querySelector("#bookings-div");

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
            RESULTS_DIV.innerHTML = "<h5>All Films</h1>";
            refreshButton();
            const RESULTS = resp.data;
            for (let result of RESULTS) {
                printResult(result);
            }
        }).catch((err) => alert(err))
}

const allBookings = () => {
    axios.get(`${ADDR}:${location.port}/booking/readAll`)
    .then((resp) => {
        BOOKINGS_DIV.innerHTML = "<h5>All Bookings</h1>";
        const RESULTS = resp.data;
        for (let result of RESULTS) {
            printBookings(result);
        }
    }).catch((err) => alert(err))

}

const titleSearch = () => {
    const SEARCH_FORM = document.forms["searchForm"];
    if (!validateSearch([SEARCH_FORM["title"].value])) {
        return;
    }

    $("#search-modal").modal("hide");
    axios.get(`${ADDR}:${location.port}/films/findByTitle/${SEARCH_FORM["title"].value}`)
    .then((resp) => {
        RESULTS_DIV.innerHTML = "<h5>Search Results</h1>";
        backButton();
        const RESULTS = resp.data;
        for (let result of RESULTS) {
            printResult(result);
        }
    }).catch((err) => {
        if (err.request.status == 404){
            alert(err.response.data.message);
        } else {
            alert(err);
        }
    })
}

const genreSearch = () => {
    const SEARCH_FORM = document.forms["searchForm"];
    if (!validateSearch([SEARCH_FORM["genre"].value])) {
        return;
    }
    
    $("#search-modal").modal("hide");
    axios.get(`${ADDR}:${location.port}/films/findByGenre/${SEARCH_FORM["genre"].value}`)
    .then((resp) => {
        RESULTS_DIV.innerHTML = "<h5>Search Results</h1>";
        backButton();
        const RESULTS = resp.data;
        for (let result of RESULTS) {
            printResult(result);
        }
    }).catch((err) => {
        if (err.request.status == 404){
            alert(err.response.data.message);
        } else {
            alert(err);
        }
    })
}

const yearSearch = () => {
    const SEARCH_FORM = document.forms["searchForm"];
    if (!validateSearch([SEARCH_FORM["year"].value, SEARCH_FORM["year2"].value])) {
        return;
    }

    $("#search-modal").modal("hide");
    axios.get(`${ADDR}:${location.port}/films/findByYearRange/${SEARCH_FORM["year"].value}/${SEARCH_FORM["year2"].value}`)
    .then((resp) => {
        RESULTS_DIV.innerHTML = "<h5>Search Results</h1>";
        backButton();
        const RESULTS = resp.data;
        for (let result of RESULTS) {
            printResult(result);
        }
    }).catch((err) => {
        if (err.request.status == 404 || err.request.status == 400){
            alert(err.response.data.message);
        } else {
            alert(err);
        }
    })
}

const minSearch = () => {
    const SEARCH_FORM = document.forms["searchForm"];
    if (!validateSearch([SEARCH_FORM["duration"].value])) {
        return;
    }

    $("#search-modal").modal("hide");
    axios.get(`${ADDR}:${location.port}/films/findByMinDuration/${SEARCH_FORM["duration"].value}`)
    .then((resp) => {
        RESULTS_DIV.innerHTML = "<h5>Search Results</h1>";
        backButton();
        const RESULTS = resp.data;
        for (let result of RESULTS) {
            printResult(result);
        }
    }).catch((err) => {
        if (err.request.status == 404){
            alert(err.response.data.message);
        } else {
            alert(err);
        }
    })
}

const maxSearch = () => {
    const SEARCH_FORM = document.forms["searchForm"];
    if (!validateSearch([SEARCH_FORM["duration"].value])) {
        return;
    }

    $("#search-modal").modal("hide");
    axios.get(`${ADDR}:${location.port}/films/findByMaxDuration/${SEARCH_FORM["duration"].value}`)
    .then((resp) => {
        RESULTS_DIV.innerHTML = "<h5>Search Results</h1>";
        backButton();
        const RESULTS = resp.data;
        for (let result of RESULTS) {
            printResult(result);
        }
    }).catch((err) => {
        if (err.request.status == 404){
            alert(err.response.data.message);
        } else {
            alert(err);
        }
    })
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

const makeBooking = () => {
    const BOOK_FORM = document.forms["bookForm"];
    if (!validateSearch([BOOK_FORM["date"].value, BOOK_FORM["time"].value])) {
        return;
    }



    let filmObj = {
        "id": BOOK_FORM["film-id"].value,
        "title": BOOK_FORM["title"].value,
        "genre": BOOK_FORM["film-genre"].value,
        "year": BOOK_FORM["film-year"].value,
        "duration" : BOOK_FORM["film-duration"].value
    }

    let obj = {
        "date": BOOK_FORM["date"].value,
        "time": BOOK_FORM["time"].value,
        "film": filmObj
    };

    console.log(obj);

    axios.post(`${ADDR}:${location.port}/booking/create`, obj)
        .then((resp) => {
            statusMsg(true);
            $("#book-modal").modal("hide");
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
            $("#edit-modal").modal("hide");
            getAll();
        }).catch((err) => {
            if (err.request.status == 404){
                alert("Film no longer in database");
                getAll();
            } else {
                alert(err);
            }
        });
}

// Delete request
const del = (id) => {
    axios.delete(`${ADDR}:${location.port}/films/delete/${id}`)
        .then((resp) => {
            getAll();
        }).catch((err) => {
            if (err.request.status == 404){
                alert("Film no longer in database");
                getAll();
            } else {
                alert(err);
            }
        })
}

const delBooking = (id) => {
    axios.delete(`${ADDR}:${location.port}/booking/delete/${id}`)
        .then((resp) => {
            allBookings();
        }).catch((err) => {
            if (err.request.status == 404){
                alert("Booking no longer in database");
                getAll();
            } else {
                alert(err);
            }
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
    
    const BOOK = document.createElement("button");
    BOOK.type = "button";
    BOOK.textContent = "Book";
    BOOK.id = `${result.id}`;
    BOOK.setAttribute("class", "btn btn-sm btn-primary book-btn");
    BOOK.setAttribute("onClick", "openBook(this.id)");

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
	ENTRY_DIV.appendChild(BOOK);
    ENTRY_DIV.appendChild(EDIT);
    ENTRY_DIV.appendChild(DEL);
    
    RESULTS_DIV.appendChild(ENTRY_DIV);
}

const printBookings = (result) => {
    const ENTRY_DIV = document.createElement("div");
    ENTRY_DIV.setAttribute("class", "entry-div");

    const ENTRY = document.createElement("div");
    ENTRY.setAttribute("class", "entry");

    const VALUES = document.createElement("div");
    VALUES.setAttribute("class", "entry-values");
    VALUES.textContent = `${result.date} | ${result.time.slice(0, -3)} | ${result.title} `;
    

    const DEL = document.createElement("button");
    DEL.type = "button";
    DEL.textContent = "Delete";
    DEL.id = `${result.id}`;
    DEL.setAttribute("class", "btn btn-sm btn-danger del-btn");
    DEL.setAttribute("onClick", "delBooking(this.id)");

    ENTRY.appendChild(VALUES);
    ENTRY_DIV.appendChild(ENTRY);
    ENTRY_DIV.appendChild(DEL);
    
    BOOKINGS_DIV.appendChild(ENTRY_DIV);
}

const backButton = () => {
    const BACK = document.createElement("button");
    BACK.type = "button";
    BACK.textContent = "BACK";
    BACK.setAttribute("class", "btn btn-sm btn-primary edit-btn");
    BACK.setAttribute("onClick", "getAll()");

    RESULTS_DIV.appendChild(BACK);
}

const refreshButton = () => {
    const BACK = document.createElement("button");
    BACK.type = "button";
    BACK.textContent = "Refresh";
    BACK.setAttribute("class", "btn btn-sm btn-primary edit-btn");
    BACK.setAttribute("onClick", "getAll()");

    RESULTS_DIV.appendChild(BACK);
}

const validateForm = (type) => {
    const CREATE_FORM = document.forms[`${type}Form`];
    if (CREATE_FORM["title"].value == "" || CREATE_FORM["genre"].value == "" || CREATE_FORM["year"].value == "" || CREATE_FORM["duration"].value == "") {
        alert("All fields require a value!");
        return false;
    }
    return true;
}

const validateSearch = (array) => {
    if (array.indexOf("") != -1) {
        alert("Empty search field!");
        return false;
    }
    return true;
}

const openBook = (id) => {
    $("#book-modal").modal("show");
    const BOOK_FORM = document.forms["bookForm"];
	axios.get(`${ADDR}:${location.port}/films/readOne/${id}`)
        .then((resp) => {
            const ENTRY = resp.data;
			BOOK_FORM["title"].value = ENTRY.title;
            BOOK_FORM["film-id"].value = ENTRY.id;
            BOOK_FORM["film-genre"].value = ENTRY.genre;
            BOOK_FORM["film-year"].value = ENTRY.year;
            BOOK_FORM["film-duration"].value = ENTRY.duration;
        }).catch((err) => {
            if (err.request.status == 404){
                alert("Film no longer in database");
                getAll();
            } else {
                alert(err);
            }
        });
}

const openEdit = (id) => {
    // Show modal and configure date field
    $("#edit-modal").modal("show");
    datepickerSetup();
    const modal1 = this;
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
        }).catch((err) => {
            if (err.request.status == 404){
                alert("Film no longer in database");
                getAll();
            } else {
                alert(err);
            }
        });
    
}

const openSearch = () => {
    $("#search-modal").modal("show");
    document.forms["searchForm"].reset();
    datepickerSetup();
}

const datepickerSetup = () => {
    // Sets to year only
    $(".year-input").datepicker({
        format: "yyyy",
        viewMode: "years",
        minViewMode: "years"
    });
}