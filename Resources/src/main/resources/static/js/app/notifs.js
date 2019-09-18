(function () {
    $(document).ready(function (e) {
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "newestOnTop": false,
            "progressBar": true,
            "positionClass": "toast-top-right",
            "preventDuplicates": false,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "5000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }
    });
})();

function notify_registration_success() {
    show_toast("Bievenu(e) !", "Inscription réussie ! <br/> veuillez vous connecter.", "success", 7000);
}

function notify_password_reset_token_success() {
    show_toast("Votre demande a été validée", "Veuillez consulter votre boite mail pour procéder à la mise à jour de votre mot de passe", "success", 10000)
}

function notify_unconfirmed_compte() {
    show_toast("Compte non-validé", "Veuillez consulter votre boite mail pour procéder à la confirmation de votre inscription", "warning", 12000)
}

function show_toast(title, content, type, delay) {
    toastr.options.timeOut = delay;
    toastr.options.extendedTimeOut = delay;

    toastr[type](content, title);
}
