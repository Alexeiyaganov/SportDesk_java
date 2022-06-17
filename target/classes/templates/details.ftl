<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>

<div>
    <div><span>${message.name}</span></div>
    <div><#if message.filename??><a href="/img/${message.filename}" data-lightbox="image-1" data-title="${message.name}" data-alt="Превью мероприятия">
            <img src="/img/${message.filename}" class="modal-dialog modal-dialog-centered"  style="max-height: 50px" ></a></#if></div>
    <div>Вид спорта: <i>#${message.tag}</i></div>
    <div>Дата и время проведения: <span>${message.date}</span></div>
    <div>Место:</div>
    <input type="hidden" id="xview" value="${message.latitude}">
    <input type="hidden" id="yview" value="${message.longitude}">
    <input type="hidden" id="location_view" value="${message.location}">
    <div id="map_view" style="width: 600px; height: 400px"></div>
    <div>Описание мероприятия: <span>${message.text}</span></div>
    <div><small class="text-muted">Последнее редактирование в: ${message.lastupdate}</small></div>
    <div>Организатор: <a href="/user-messages/${message.authorId}">${message.authorName}</a></div>
    <#--<div><#if user??><a class="btn btn-primary" href="/user-sportsmen/${sportsman.author.id}?message=${message.id}">Заявиться</a><#else>Чтобы заявиться на данное мероприятие, необходимо авторизоваться</#if></div>-->

</div>
</@c.page>