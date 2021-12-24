        function sendNoteToCreate(){
            const WRONG_NAME_LENGTH = "The name mast be at least 5 symbols, up to 100 symbols";
            const WRONG_DESCRIPTION_LENGTH = "The description must be at least 5 symbols, up to 10000 symbols";
            let titleErrorField = document.querySelector('.titleErrorField');
            titleErrorField.innerHTML = null;
            let contentErrorField = document.querySelector('.contentErrorField');
            contentErrorField.innerHTML = null;
            let name = document.querySelector('#name');
            let description = document.querySelector('#description');
            let request = new XMLHttpRequest();
            let url = "/group/create";
            request.open("POST", url, true);
            request.setRequestHeader("Content-Type", "application/json");
            request.responseType='json'
                request.onreadystatechange = function () {
                 if (request.readyState === 4 && request.status === 200) {
                    var operationStatus = request.response;
                    if (operationStatus.success===true){
                        window.location.href = '/group/list';
                    } else{
                        operationStatus.errors.forEach(function(error) {
                            switch (error){
                            case 'WRONG_GROUP_NAME_LENGTH' :
                                titleErrorField.innerHTML = WRONG_NAME_LENGTH;
                                break;
                            case 'WRONG_GROUP_DESCRIPTION_LENGTH':
                                contentErrorField.innerHTML = WRONG_DESCRIPTION_LENGTH;
                                break;
                            }
                            })
                 }}
            };
            var data = JSON.stringify({ "name": name.value, "description": description.value});
            request.send(data);
        }