
let diaryList = [];
let server_url = 'http://3.22.3.98:80'; //실제 동작하는 서버입니다. 접속 가능합니다.
// let server_url = 'http://localhost:80';

(function init() {
    server_url = 'http://3.22.3.98:80';
    // server_url = 'http://localhost:80';
    getList(null);
}());

function formSubmit() {
    //폼을 입력하는 곳의 동작입니다.
    //불필요한 데이터를 막기 위해, UX 예외처리를 하였습니다.

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

    // 새로운 일기를 POST 요청을 통해 axios 비동기로 처리합니다.
    axios.post(server_url+'/api/NewDiary', null, { params: { name: inputName, password: inputPassword, text: inputText } })
        .then(() => getList(null)); //화면 그리기 비동기 처리

    // axios.request({
    //     method: 'POST',
    //     url: `http://localhost:3000/api/NewDiary`,
    //     headers: {'Content-Type': 'application/json'},
    //     params: {"name": inputName, "password": inputPassword, "text": inputText},
    // }).then(() => getList());

}

//일기의 리스트를 가져오는 곳입니다.

//발표와 ppt에서는 생략되었는데,
//이름으로 내가 쓴 일기만을 표시할 수 있게끔 기능을 만들어보았습니다.
function drawTable(searchValue) {
    //여기서 테이블은 일기의 리스트를 담는 곳입니다.
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
    //회원들이 총 얼마를 아꼈는지, 통계를 업데이트해주는 영역입니다.
    document.getElementById("totalTextOriginal").innerHTML = data.totalTextOriginal+'bit';
    document.getElementById("totalTextCompress").innerHTML = data.totalTextCompress+'bit';
    document.getElementById("savedMoney").innerHTML = data.totalTextOriginal - data.totalTextCompress+'bit';
}

function getList() {
    //모든 다이어리 리스트를 가져옵니다.
    //객체로 전체 다이어리의 간략한 정보만 가져오고,
    //렌더링 해주는 방식입니다.

    //비동기 처리로 동작합니다.
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

    //작성한 다이어리를 열람할 때 동작입니다.
    //여기서부터는 alert 과 confirm을 기반으로 단계별 동작합니다.
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

                //패스워드를 입력하고, 실제 원본 데이터를 불러오는 영역입니다.
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

//이름으로 검색을 가능하게 했습니다.
function searchBoxEvent() {
    drawTable(document.getElementById("searchBox").value);
}