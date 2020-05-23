function logout( e ){
    e.preventDefault();

    document.getElementById( "logout-form" ).submit();
}

function index(){
    const logoutDOM = document.getElementById( "logout" );

    if( logoutDOM !== null )
        logoutDOM.addEventListener( "click", logout );
}

window.addEventListener( "load", index );