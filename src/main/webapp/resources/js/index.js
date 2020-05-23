function index(){
    const cards = document.getElementsByClassName( "card" );

    for( let i = 0; i < cards.length; i++ ){
        const card = cards[i];
        const cardLink = card.getElementsByClassName( "cardLink" )[0];

        card.addEventListener( "click", () => cardLink.click() );
    }
}

window.addEventListener( "load", index );