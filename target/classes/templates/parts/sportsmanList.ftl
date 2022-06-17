<#include "security.ftl">
<#import "pager.ftl" as p>

<@p.pager url page/>


    <div class='color container-fluid ' >
<br>
<div class="row align-items-center border border-secondary" >
    <div class="col"><span>Имя</span></div>
    <div class="col"><span>Фамилия</span></div>


</div>
<#list page.content as sportsman>
    <#if  user == sportsman.author>
        <div class="row align-items-center border border-secondary" </#if>>
            <div class="col"><span>${sportsman.name}</span></div>
            <div class="col"><span>${sportsman.lastname}</span></div>


            <div class="col"><#if sportsman.author.id == currentUserId><a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">Редактировать</a></#if></div>

        </div>


        </div>

    </#if>
<#else >
    Нет новостей
</#list>
</div>
<@p.pager url page/>