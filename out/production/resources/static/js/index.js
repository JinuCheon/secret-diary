
let diaryList = [];
// let server_url = 'http://3.22.3.98:80';
let server_url = 'http://localhost:80';

(function init() {
    // server_url = 'http://3.22.3.98:80';
    server_url = 'http://localhost:80';
    getList(null);
}());

function formSubmit() {
    if(document.getElementById("name").value.length < 2){
        alert("이름을 두 글자 이상 입력해주세요!");
        return;
    }
    if(document.getElementById("password").value.length < 4){
        alert("비밀번호를 4글자 이상 입력해주세요!");
        return;
    }
    if(document.getElementById("text").value.length < 5){
        alert("일기 내용을 5글자 이상 입력해주세요!");
        return;
    }
    let inputName = document.getElementById("name").value;
    let inputPassword = document.getElementById("password").value;
    let inputText = document.getElementById("text").value;

    document.getElementById("name").value = "";
    document.getElementById("password").value = "";
    document.getElementById("text").value = "";

    console.log(inputName, inputPassword, inputText);
    axios.post(server_url+'/api/NewDiary', null, { params: { name: inputName, password: inputPassword, text: inputText } })
        .then(() => getList(null)); //화면 그리기 비동기 처리

    // axios.request({
    //     method: 'POST',
    //     url: `http://localhost:3000/api/NewDiary`,
    //     headers: {'Content-Type': 'application/json'},
    //     params: {"name": inputName, "password": inputPassword, "text": inputText},
    // }).then(() => getList());

}
function drawTable(searchValue) {
    console.log(searchValue)
    let diaryTable = "<table><th>글번호</th><th>이름</th><th>작성시간</th>"
    for (let i = diaryList.length - 1; i >= 0; i--) {
        if (searchValue == "" || searchValue == null || searchValue == diaryList[i].name)
            diaryTable += `<tr><td>${diaryList[i].id}</td><td>${diaryList[i].name}</td><td>${diaryList[i].time}</td><td><button onclick="openDiary(${diaryList[i].id})">일기 열람</button></td></tr>`
    }
    diaryTable += "</table>"
    document.getElementById("list").innerHTML = diaryTable;
}

function drawStatisticsBox(data) {
    document.getElementById("totalTextOriginal").innerHTML = data.totalTextOriginal+'bit';
    document.getElementById("totalTextCompress").innerHTML = data.totalTextCompress+'bit';
    document.getElementById("savedMoney").innerHTML = data.totalTextOriginal - data.totalTextCompress+'bit';
}

function getList() {
    axios.get(server_url+'/api/DiaryList')
        .then(function (response) {
            console.log(response);
            diaryList = response.data.diaryList;
            drawStatisticsBox(response.data);
            drawTable(null);
        })
        .catch(function (error) {
            console.log(error);
        })
}

function openDiary(num) {
    axios.get(server_url+'/api/DiaryInfo', { params: { id: num } })
        .then(function (response) {
            let compressRate = Math.ceil(response.data.lengthOfCompressed / response.data.lengthOfOriginal * 100);
            let cryptoText = response.data.cryptoText;
            cryptoText = cryptoText.replace(/(.{50})/g,"$1\n")
            let promptMessage =
                `원본 일기 비용 : ${response.data.lengthOfOriginal} / 압축된 일기 비용 : ${response.data.lengthOfCompressed} / 압축률 : ${compressRate}%
허프만 알고리즘으로 무려 ${response.data.lengthOfOriginal - response.data.lengthOfCompressed}원을 아꼈어요!

서버에 저장된 암호문 :
${cryptoText}`
            //let password = prompt(promptMessage, "암호 입력");
            const getPassword = async () => {
                const { value: password } = await swal.fire({
                    // title: "Enter your password",
                    input: "password",
                    inputLabel: promptMessage,
                    inputPlaceholder: "Enter your password",
                    inputAttributes: {
                        maxlength: 10,
                        autocapitalize: "off",
                        autocorrect: "off",
                    },
                });
                if (password) {
                    axios.post(server_url+'/api/DecodeDiary', null, { params: { id: num, password: password } })
                        .then(function (response) {
                            console.log(response.data.cryptoText);
                            Swal.fire(
                                '원본 일기',
                                response.data,
                                'info'
                            );
                        })
                        .catch(function (error) {
                        })
                }
            };
            getPassword();



            //패스워드, index 보내서 평문 복구
        })
        .catch(function (error) {
        })
}

function searchBoxEvent() {
    drawTable(document.getElementById("searchBox").value);
}