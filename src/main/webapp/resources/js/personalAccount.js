async function incBalance(){
    const value = document.getElementById( "moreBalance" ).value;
    const result = await API.users.incBalance( value );

    console.log( result );
}

function index(){
    document.getElementById( "incBalance" ).addEventListener( "click", incBalance );
}

window.addEventListener( "load", index );