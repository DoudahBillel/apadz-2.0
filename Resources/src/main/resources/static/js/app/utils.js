(function () {
    $(document).ready(function () {
        $.fn.datepicker.dates['fr'] = {
            days: ["Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"],
            daysShort: ["Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam"],
            daysMin: ["Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa"],
            months: ["Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"],
            monthsShort: ["Jan", "Fév", "Mar", "Avr", "Mai", "Jui", "Jui", "Aoû", "Sep", "Oct", "Nov", "Dec"],
            today: "Aujourd'hui",
            clear: "Vider",
            format: "dd/mm/yyyy",
            titleFormat: "MM yyyy",
            weekStart: 0
        };

        $('.datepickers').datepicker({
            calendarWeeks: true,
            autoclose: true,
            todayHighlight: true,
            format: "dd/mm/yyyy",
            language: "fr"
        });

        $('input[ms-twin-input]').each(function (index, master) {
            $(master).keyup(function (e) {
                var slave = $("#" + $(this).attr("ms-twin-input"));

                var masterValue = $(master).val();
                var slaveValue = $(slave).val();

                if (masterValue == slaveValue) {
                    $(master).removeClass("is-invalid");
                    $(slave).removeClass(("is-invalid"));

                    $(master).addClass("is-valid");
                    $(slave).addClass(("is-valid"));
                } else {
                    $(master).removeClass("is-valid");
                    $(slave).removeClass(("is-valid"));

                    $(master).addClass("is-invalid");
                    $(slave).addClass(("is-invalid"));
                }
            });
        });

        $('.post-json-form').each(function (index, form) {
            $(form).submit(function (e) {
                e.preventDefault();

                $(form).find(".invalid-feedback").remove();
                $(form).find(".is-invalid").removeClass("is-invalid");

                var url = $(this).attr("action");
                var json = formatFormAsJson(form);
                var method = $(this).attr("method");
                var callback = $(this).attr("ms-success-callback");
                var notifyOnSuccess = $(this).attr("ms-notify-on-success");
                var clearOnSuccess = $(this).attr("ms-clear-on-success") == "true";
                var notificationDelay = $(this).attr("ms-notification-delay");

                $.ajax({
                    type: method,
                    url: url,
                    data: json,
                    success: function (param) {
                        var type = param.error ? "error" : "success";
                        var content = param.message;
                        var title = param.title;

                        if (notifyOnSuccess) {
                            show_toast(title, content, type, notificationDelay);
                        }

                        if (callback) {
                            if (typeof window[callback] == "function") {

                                window[callback](param);
                            }
                        }

                        if (clearOnSuccess) {
                            form.reset();
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        var response = JSON.parse(XMLHttpRequest.responseText);

                        var fieldName = response.fieldName;
                        var message = response.message;

                        var input = $(form).find($(
                            "input[name='" + fieldName + "'], " +
                            "select[name='" + fieldName + "']"));

                        $(input).addClass("is-invalid");
                        $(input).parent().append("<div class='invalid-feedback'>"
                            + message + "</div>");
                    },

                    dataType: "json",
                    contentType: "application/json"
                });
            });
        });

        $('.unidirectionnal-forward-slider').each(function (index, slider) {
            var min = parseInt($(this).attr("min-value"), 10);
            var max = parseInt($(this).attr("max-value"), 10);
            var disableComma = $(this).attr("disable-comma");

            if (disableComma == 'true') {
                $(slider).customSlider({
                    start: 0,
                    connect: [true, false],
                    tooltips: true,
                    range: {
                        'min': min,
                        'max': max
                    },
                    format: {
                        to: function (value) {
                            return parseInt(value + "", 10);
                        },

                        from: function (value) {
                            return parseInt(value, 10);
                        }
                    }
                });
            } else {
                $(slider).customSlider({
                    start: 0,
                    connect: [true, false],
                    tooltips: true,
                    range: {
                        'min': min,
                        'max': max
                    }
                });
            }
        });
    });
})();

// ----------------------------------------------------------------------------------------

function add_entity_as_option(parentSelector, entity, serviceUri, designationKey) {
    var option = "<option value=" + serviceUri + entity.id + ">"
        + entity[designationKey] + "";

    $(parentSelector).append(option);
}

// ----------------------------------------------------------------------------------------

function formatFormAsJson(form) {
    var unindexed_array = $(form).serializeArray();
    var indexed_array = {};

    $(form).find(".valuated-block").each(function(index, element){
        indexed_array[$(element).attr("name")] = $(element).html();
    });

    $.map(unindexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });

    return JSON.stringify(indexed_array);
}