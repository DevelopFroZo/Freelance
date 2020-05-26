async function fillSolutions( solutions ){
    if( solutions.length === 1 && solutions[0] === null )
        return document.getElementById( "solutionsEmpty" ).classList.remove( "hidden" );

    const userIds = solutions.map( ( { executor: { id } } ) => id );
    const executorsRatings = await API.executorRating.getByUserIds( userIds );
    const solutionsList = document.getElementById( "solutionsList" );

    for( let solution of solutions ){
        solution.executor.rating = {};

        if( executorsRatings !== 403 )
            for( let executorRating of executorsRatings )
                if( solution.executor.id === executorRating.userId )
                    solution.executor.rating[ executorRating.category ] = executorRating.rating;
    }

    solutionsList.classList.remove( "hidden" );

    // FIXME change to normal solutions
    for( let solution of solutions ){
        const li = document.createElement( "li" );
        solutionsList.appendChild( li );

        const pre = document.createElement( "pre" );
        li.appendChild( pre );

        const code = document.createElement( "code" );
        pre.appendChild( code );
        pre.innerHTML = JSON.stringify( solution, null, 2 );
    }
}

async function index(){
    const id = ( new URLSearchParams( window.location.search ) ).get( "id" );
    const solutions = await API.solutions.getByTaskId( root, id );

    if( solutions === 403 )
        document.getElementById( "solutionsUnauthorized" ).classList.remove( "hidden" );
    else
        fillSolutions( solutions );
}

window.addEventListener( "load", index );