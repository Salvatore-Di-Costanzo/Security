$("#logout").on("submit",function(){
        fetch('/logout').then(response => response.ok)
})