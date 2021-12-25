        function sendNoteToCreate(){
            const WRONG_TITLE_LENGTH = "The title mast be at least 5 symbols, up to 100 symbols";
            const WRONG_NOTE_LENGTH = "The note must be at least 5 symbols, up to 10000 symbols";
            let titleErrorField = document.querySelector('.titleErrorField');
            titleErrorField.innerHTML = null;
            let contentErrorField = document.querySelector('.contentErrorField');
            contentErrorField.innerHTML = null;
            let title = document.querySelector('#title');
            let text = document.querySelector('#note');
            let uuid = document.querySelector('#group');
            let request = new XMLHttpRequest();
            let url = "/groupNote/create";
            request.open("POST", url, true);
            request.setRequestHeader("Content-Type", "application/json");
            request.responseType='json'
                request.onreadystatechange = function () {
                 if (request.readyState === 4 && request.status === 200) {
                    var operationStatus = request.response;
                    if (operationStatus.success===true){
                        window.location.href = '/';
                    } else{
                        operationStatus.errors.forEach(function(error) {
                            switch (error){
                            case 'WRONG_NOTE_TITLE_LENGTH' :
                                titleErrorField.innerHTML = WRONG_TITLE_LENGTH;
                                break;
                            case 'WRONG_NOTE_CONTENT_LENGTH':
                                contentErrorField.innerHTML = WRONG_NOTE_LENGTH;
                                break;
                            }
                            })
                 }}
            };
            var data = JSON.stringify({ "title": title.value, "content": text.value, "groupUUID": uuid.value});
            request.send(data);
        }