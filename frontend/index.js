function showAlert() {
    var alert = document.getElementById('alert');
    alert.classList.add('show');
    
    // Fechar o alerta após 3 segundos
    setTimeout(function() {
      alert.classList.remove('show');
    }, 3000);
  }


//Controle formulário
$(document).ready(function() {
    function checkFormCompletion() {
        let isFormComplete = true;

        // Verifica os campos de input e select obrigatórios
        $('#curriculo-form input[required], #curriculo-form select[required], #curriculo-form textarea[required], #curriculo-form input[type="file"][required]').each(function() {
            // Verifica se o campo está vazio ou não selecionado
            if ($(this).val().trim() === '' || $(this).val() === null || $(this).val() === undefined) {
                isFormComplete = false;
            }
        });

        // Atualiza o estado do botão (habilitado/desabilitado)
        if (isFormComplete) {
            $('#submit-button').prop('disabled', false).css('cursor', 'pointer');  // Habilita o botão e altera o cursor
        } else {
            $('#submit-button').prop('disabled', true).css('cursor', 'not-allowed');  // Desabilita o botão e altera o cursor
        }
    }

    // Adiciona eventos para verificar os campos de input, select, textarea e file
    $('#curriculo-form input[required], #curriculo-form select[required], #curriculo-form textarea[required], #curriculo-form input[type="file"][required]').on('keyup change', function() {
        checkFormCompletion();
    });

    // Chama a função para verificar o formulário quando a página for carregada
    checkFormCompletion();
});


//Get escolaridades
$(document).ready(function () {
    const apiUrl = "http://localhost:8080/escolaridades";

    $.ajax({
        url: apiUrl,
        type: "GET",
        dataType: "json",
        success: function (response) {
            if (Array.isArray(response)) {
                response.forEach(function (item) {
                    $("#escolaridades").append(
                        `<option value="${item}">${item}</option>`
                    );
                });
            } else {
                console.error("O retorno não é um array:", response);
            }
        },
        error: function (xhr, status, error) {
            console.error("Erro ao buscar os dados:", error);
        },
    });
});


// Post form
$(document).ready(function(){
    $("#curriculo-form").submit(function(event){
        event.preventDefault();

        // Criando um novo objeto FormData
        var formData = new FormData();

        // Coletando os dados do formulário e adicionando ao FormData
        formData.append("nome", $("#name-user-icon").val());
        formData.append("email", $("#email-address-icon").val());
        formData.append("telefone", $("#phone-input").val());
        formData.append("cargoDesejado", $("#cargo-desejado-icon").val());
        formData.append("observacoes",$("#message").val())
        formData.append("escolaridade", $("#escolaridades").val());

        
        const fileInput = $("#file_input")[0]; // Obtém o elemento HTML puro

        // Verifica se um arquivo foi selecionado
        if (fileInput && fileInput.files.length > 0) {
            formData.append("arquivo", fileInput.files[0]);  // Adiciona o arquivo ao formData
        }


        // Enviando a requisição AJAX
        var settings = {
            "url": "http://localhost:8080/api/curriculos/enviar",
            "method": "POST",
            "timeout": 0,
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": formData
        };

        $.ajax(settings).done(function(response) {
            showAlert();
            event.target.reset();
      
        }).fail(function(xhr, status, error) {
            console.error("Erro na requisição:", error);
            alert("Erro ao enviar os dados.");
        })
        .always(function(){
            $("loading").hide();
        })
    });
});

