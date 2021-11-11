
(function init(){
    getList();
}());

function formSubmit(){
    let inputName = document.getElementById("name").value;
    let inputPassword = document.getElementById("password").value;
    let inputText = document.getElementById("text").value;
    console.log(inputName, inputPassword, inputText);
    axios.request({
        method: 'POST',
        url: `http://localhost:3000/api/NewDiary`,
        headers: {'Content-Type': 'application/json'},
        params: {"name": inputName, "password": inputPassword, "text": inputText},
    }).then(() => getList());
}

function getList() {
    axios.get('http://localhost:3000/api/DiaryList')
        .then(function (response) {
            let diaryList = response.data;
            console.log(diaryList);

            let diaryTable = "<table>"
            for(let i=0;i<diaryList.length;i++){
                diaryTable+=`<tr><td>${diaryList[i].id}</td><td>${diaryList[i].name}</td><td>${diaryList[i].time}</td><td><button>일기 열람</button></td></tr>`
            }
            diaryTable += "</table>"
            console.log(diaryTable);
            document.getElementById("list").innerHTML = diaryTable;
        })
        .catch(function (error) {
            console.log(error);
        })
        .finally(function () {
            // always executed
        });
}


