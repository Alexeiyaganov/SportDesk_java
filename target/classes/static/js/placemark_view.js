ymaps.ready(function () {
    var ypoint= document.getElementById("yview").value;
    var xpoint= document.getElementById("xview").value;
    var name_point= document.getElementById("location_view").value;

    var x = parseFloat(xpoint);
    var y = parseFloat(ypoint);
    console.log(xpoint);
    console.log(ypoint);
    var myMap = new ymaps.Map('map_view', {
            center: [x, y],
            zoom: 9
        }, {
            searchControlProvider: 'yandex#search'
        });



    myMap.geoObjects
        .add(new ymaps.Placemark([x, y], {
            balloonContent: name_point
        }, {
            preset: 'islands#icon',
            iconColor: '#0009b6'
        }))
});