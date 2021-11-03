var idUsuario = 0;
var nickUsuario = "";
var proyectoActual = "";

function siguienteCampo(actual, siguiente, preventDefault) {
    $(actual).keydown(function (event) {
        if (event.which === 13) {
            if (preventDefault) {
                event.preventExtensions();
            }
            $(siguiente).focus();
            $(siguiente).select();
        }
    });
}

function enterCampo(actual, ejecutar) {
    $(actual).keydown(function (event) {
        if (event.which === 13) {
            eval(ejecutar);
        }
    });
}


function setDatosSesion(id_usuario, login_usuario) {
    idUsuario = id_usuario;
    nickUsuario = login_usuario;
}
