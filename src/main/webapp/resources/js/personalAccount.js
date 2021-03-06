async function incBalance(){
    const value = parseInt( document.getElementById( "moreBalance" ).value );
    const result = await API.users.incBalance( value );

    if( !result.ok )
        return alert( result.message );

    const oldBalanceInput = document.getElementById( "valueBalance" );
    const oldBalance = parseInt( oldBalanceInput.innerHTML );

    oldBalanceInput.innerHTML = oldBalance + value;
}

function index(){
    document.getElementById( "incBalance" ).addEventListener( "click", incBalance );
}

window.addEventListener( "load", index );