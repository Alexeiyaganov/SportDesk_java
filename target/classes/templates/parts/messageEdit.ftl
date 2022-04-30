<#include "security.ftl">
<#if user??>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить мероприятие
    </a>

    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.name}</#if>" name="name" placeholder="Введите название" />
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}

                        </div>
                    </#if>
                </div>

                <div class="form-group ${(dateError??)?string('is-invalid', '')}">
                    <input type="datetime-local" class="form-control " value="<#if message??>${message.date}</#if>"
                           name="localDateTime" />
                    <#if dateError??>
                        <div class="invalid-feedback">
                            ${dateError}

                        </div>
                    </#if>

                </div>




                <div class="form-group">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.text}</#if>" name="text" placeholder="Введите описание мероприятия" />
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}

                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control"
                           value="<#if message??>${message.tag}</#if>" name="tag" placeholder="Тэг">
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}

                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="id" value="<#if message??>${message.id}</#if>" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</#if>
<br>