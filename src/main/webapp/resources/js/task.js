let solutions_;

function fillModal( index ){
    const solution = solutions_[ index ];
    const executor = solution.executor;
    const rateUser = document.getElementById( "rateUser" );

    document.getElementById( "solutionDescription" ).innerHTML = solution.description;
    document.getElementById( "solutionExecutorName" ).innerHTML = executor.name;
    document.getElementById( "solutionExecutorDescription" ).innerHTML = executor.description;

    rateUser.innerHTML = "";

    for( let key in executor.rating )
        rateUser.innerHTML +=
            `<div class = "row">
                <p class = "col-6">${key}</p>
                <p class = "col-6">${executor.rating[ key ]}</p>
            </div>`;
}

async function fillSolutions(){
    if( solutions_.length === 1 && solutions_[0] === null )
        return document.getElementById( "solutionsEmpty" ).classList.remove( "hidden" );

    const userIds = solutions_.map( ( { executor: { id } } ) => id );
    const executorsRatings = await API.executorRating.getByUserIds( userIds );
    const solutionsCards = document.getElementById( "solutionsCards" );

    for( let solution of solutions_ ){
        solution.executor.rating = {};

        if( executorsRatings !== 403 )
            for( let executorRating of executorsRatings )
                if( solution.executor.id === executorRating.userId )
                    solution.executor.rating[ executorRating.category ] = executorRating.rating;
    }

    solutionsCards.classList.remove( "hidden" );

    for( let [ i, solution ] of solutions_.entries() )
        solutionsCards.innerHTML +=
            `<div class = "card">
                <div class = "card-header">
                    <p>${solution.executor.name}</p>
                </div>
                <div class = "card-body">
                    <p class = "card-text">${solution.description}</p>
                    <button type = "button" class = "btn btn-primary" data-toggle = "modal" data-target = "#modalAnswer" onclick = "fillModal( ${i} )">
                        See more
                    </button>
                </div>
            </div>`;
}

async function chooseSolutionFunc( taskId, solutionId ){
    const result = await API.tasks.setSolution( taskId, solutionId );

    if( result === true )
        window.open( "/", "_blank" );
    else
        alert( "Something wrong" );
}

async function index(){
    const urlSearchParams = new URLSearchParams( window.location.search );
    const id = urlSearchParams.get( "id" );
    const error = urlSearchParams.get( "error" );
    solutions_ = await API.solutions.getByTaskId( root, id );
    const currentUser = await API.users.getCurrent();

    if( typeof error === "string" ){
        if( error === "a_customer" )
            alert( "Customer doesn't add a solution to self task" );
        else if( error === "already_chosen" )
            alert( "Solution for this task already chosen" );
    }

    if( solutions_ === 403 )
        document.getElementById( "solutionsUnauthorized" ).classList.remove( "hidden" );
    else{
        const taskCustomerId = parseInt( document.getElementById( "taskCustomerId" ).value );

        if( taskCustomerId === currentUser.id ){
            const taskId = parseInt( document.getElementById( "taskId" ).value );
            const chooseSolution = document.getElementById( "chooseSolution" );

            chooseSolution.addEventListener( "click", () => chooseSolutionFunc( taskId, taskCustomerId ) );
            chooseSolution.classList.remove( "hidden" );
        }

        fillSolutions();
    }
}

window.addEventListener( "load", index );