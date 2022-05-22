
ymaps.ready(init);

function init(){
    var ypoint= document.getElementById("yypoint").value;
    var xpoint= document.getElementById("xpoint").value;
    var name_point= document.getElementById("location_point").value;
    var x = parseFloat(xpoint);
    var y = parseFloat(ypoint);
    var flag=false;
    console.log(xpoint);
    console.log(ypoint);
    console.log(name_point);
    if(!name_point){
        name_point="Место мероприятия"
    }
    if(!x){
        x=60;
        flag=true;
    }
    if(!y){
        y=34;
        flag=true;
    }

    var geolocation = ymaps.geolocation,
        myMap = new ymaps.Map('map', {
            center: [x, y],
            zoom: 10
        }, {
            searchControlProvider: 'yandex#search'
        });

    // Сравним положение, вычисленное по ip пользователя и
    // положение, вычисленное средствами браузера.
    geolocation.get({
        provider: 'auto',
        mapStateAutoApply: flag
    }).then(function (result) {
        if(!flag){
            result.geoObjects.get(0).geometry.setCoordinates([x, y]);
        }


        // Красным цветом пометим положение, вычисленное через ip.
        result.geoObjects.options.set({
            preset: 'islands#blackStretchyIcon',
            draggable: true
        });
        result.geoObjects.get(0).properties.set({
            iconContent: name_point,
            hintContent: 'Можно тащить'
        });


        result.geoObjects.events.add('contextmenu', function (e){
            // Если меню метки уже отображено, то убираем его.
            if ($('#menu').css('display') == 'block') {
                $('#name_point').val(result.geoObjects.get(0).properties.get('iconContent'));
                $('#menu').remove();
            } else {
                // HTML-содержимое контекстного меню.
                var menuContent =
                    '<div id="menu">\
                        <ul id="menu_list">\
                            <li>Название: <br /> <input type="text" name="icon_text" /></li>\
                        </ul>\
                    <div align="center"><input type="submit" value="Сохранить" /></div>\
                    </div>';

                // Размещаем контекстное меню на странице
                $('#map').append(menuContent);

                // Задаем позицию меню.
                $('#menu').css({
                    left: e.get('pagePixels')[0],
                    top: e.get('pagePixels')[1]
                });

                // Заполняем поля контекстного меню текущими значениями свойств метки.
                $('#menu input[name="icon_text"]').val(result.geoObjects.get(0).properties.get('iconContent'));


                // При нажатии на кнопку "Сохранить" изменяем свойства метки
                // значениями, введенными в форме контекстного меню.
                $('#menu input[type="submit"]').click(function () {
                    result.geoObjects.get(0).properties.set({
                        iconContent: $('input[name="icon_text"]').val()
                    });
                    $('#name_point').val(result.geoObjects.get(0).properties.get('iconContent'));
                    // Удаляем контекстное меню.
                    $('#menu').remove();
                });
            }

        });

        myMap.geoObjects.add(result.geoObjects);

        result.geoObjects.events.add('dragend', function(e) {
            $('#yandex_point').val(result.geoObjects.get(0).geometry.getCoordinates());

        });
    });


}