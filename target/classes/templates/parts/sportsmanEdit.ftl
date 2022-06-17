<#if user??>
    <a class="btn btn-primary" data-toggle="collapse" href="#register" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить спортсмена
    </a>

    <div class="collapse <#if register??>show</#if>" id="register">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                           value="<#if register??>${register.name}</#if>" name="name" placeholder="Введите имя" />
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}

                        </div>
                    </#if>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control ${(lastnameError??)?string('is-invalid', '')}"
                           value="<#if register??>${register.lastname}</#if>" name="lastname" placeholder="Введите фамилию" />
                    <#if lastnameError??>
                        <div class="invalid-feedback">
                            ${lastnameError}

                        </div>
                    </#if>
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