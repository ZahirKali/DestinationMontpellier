#=== mon code ne s'exécute qu'une fois le DOM complètement chargé ===
console.log "CoffeeScript version in progress ..."
$ ->
    #définition des différents éléments d'IHM
    console.log "dom loaded ... i hope ..."
    user = {}
    alertAuthentication = $ "div[name=authentication]"
    loginButton = $ "button[name=login]"
    logoutButton = $ "button[name=logout]"
    loadBookmarksButton = $ "button[name=loadbookmarks]"
    email = $ "input[name=email]"
    password = $ "input[name=password]"
    labels = $ "label"
    bookmarksList = $ "ul[name=bookmarks]"

    #onclick du bouton login
    console.log "OnClick login button definition ..."
    loginButton.click ->
        user.email = email.val()
        user.password = password.val()

        $.ajax 
            type:"POST"
            url:"/loginjson"
            data: 
                email : user.email
                password : user.password
            error : (err)->
                console.log "Erreur", err
            success : (data)->
                if data isnt "badRequest"
                    alertAuthentication.attr("class","alert alert-success")
                      .html "<strong>Bienvenue !</strong> #{data}"
                    user.name = data
                    labels.hide()
                    email.hide()
                    password.hide()
                    loginButton.hide()
                else
                    alertAuthentication.attr("class","alert alert-error")
                        .html "<strong>Oups !</strong> vous avez du vous tromper"

    #onclick du bouton logout
    console.log "OnClick logout button definition ..."
    logoutButton.click ->

        $.ajax 
            type:"GET"
            url:"/logoutjson"
            error : (err)->
                console.log "Erreur", err
            success : (data)->
                if user.name
                  alertAuthentication.attr("class", "alert alert-info")
                    .html "<strong>Au revoir</strong> #{user.name}"
                  labels.show()
                  email.show()
                  password.show()
                  loginButton.show()
                  user = {}
                  bookmarksList.html ""

    #onclick du bouton de chargement des bookmarks
    console.log "OnClick load bookmarks button definition ..."
    loadBookmarksButton.click ->

        $.ajax 
            type:"GET"
            url:"/bookmarks/jsonlist"
            error : (err)->
                console.log "Erreur", err
            success : (data)->
                if data isnt "failed"
                    console.log data
                    bookmarksList.html ""
                    data.bookmarks.forEach (bookmark) ->
                        bookmarksList.append $ """
                            <li><b>#{bookmark.title} | 
                            <a href='#{bookmark.url}'>#{bookmark.url}</a> | 
                            <i>#{bookmark.details}</i> | 
                            (#{bookmark.category.label})</li>
                        """
                else
                    alertAuthentication.attr("class", "alert alert-error")
                        .html "
                            <strong>
                                Il faut être authentifié !
                            </strong> pour obtenir la liste des bookmarks
                            "
