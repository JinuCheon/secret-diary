
(function init(){
    getList();
}());

function formSubmit(){
    let inputName = document.getElementById("name").value;
    let inputPassword = document.getElementById("password").value;
    let inputText = document.getElementById("text").value;
    console.log(inputName, inputPassword, inputText);
    axios.post('http://localhost:3000/api/NewDiary', null, {params: {name: inputName, password: inputPassword, text: inputText}})
        .then(() => getList());
    // axios.request({
    //     method: 'POST',
    //     url: `http://localhost:3000/api/NewDiary`,
    //     headers: {'Content-Type': 'application/json'},
    //     params: {"name": inputName, "password": inputPassword, "text": inputText},
    // }).then(() => getList());
    //화면 그리기 비동기 처리
}

function getList() {
    axios.get('http://localhost:3000/api/DiaryList')
        .then(function (response) {
            let diaryList = response.data;
            console.log(diaryList);

            let diaryTable = "<table><th>글번호</th><th>이름</th><th>작성시간</th>"
            for(let i=0;i<diaryList.length;i++){
                diaryTable+=`<tr><td>${diaryList[i].id}</td><td>${diaryList[i].name}</td><td>${diaryList[i].time}</td><td><button onclick="openDiary(${diaryList[i].id})">일기 열람</button></td></tr>`
            }
            diaryTable += "</table>"
            console.log(diaryTable);
            document.getElementById("list").innerHTML = diaryTable;
        })
        .catch(function (error) {
            console.log(error);
        })
}

function openDiary(num){
    axios.get('http://localhost:3000/api/DiaryInfo', {params: {id: num}})
        .then(function (response) {
            let password = prompt("암호문\n" + response.data, "암호 입력");

            axios.post('http://localhost:3000/api/DecodeDiary', null, {params: {id: num, password: password}})
                .then(function (response) {
                    alert("원본일기\n"+response.data);
                })
                .catch(function (error) {
                })

            //패스워드, index 보내서 평문 복구
        })
        .catch(function (error) {
        })
}
