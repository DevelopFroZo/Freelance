const solutions = {
    getByTaskId: solutionsGetByTaskId
};

const executorRating = {
    getByUserIds: executorRatingGetByUserIds
};

const users = {
    getCurrent: usersGetCurrent
};

const tasks = {
    setSolution: tasksSetSolution
};

window.API = {
    solutions,
    executorRating,
    users,
    tasks
};

async function solutionsGetByTaskId( root, id ){
    const response = await fetch( `${root}solutions/get_by_task_id?task_id=${id}` );

    if( response.status === 403 || response.redirected )
        return 403;
    else if( !response.ok )
        throw response;

    return await response.json();
}

async function executorRatingGetByUserIds( userIds ){
    const response = await fetch( `${root}executor_rating/get_by_user_ids?user_ids=${userIds}` );

    if( response.status === 403 || response.redirected )
        return 403;
    else if( !response.ok )
        throw response;

    return await response.json();
}

async function usersGetCurrent(){
    let result;
    const response = await fetch( `${root}users/current` );

    if( response.status === 403 || response.redirected )
        return 403;
    else if( !response.ok )
        throw response;

    try{
        result = await response.json();
    } catch( e ){
        result = null;
    }

    return result;
}

async function tasksSetSolution( taskId, solutionId ){
    const response = await fetch( `${root}tasks/set_solution?task_id=${taskId}&solution_id=${solutionId}` );

    if( response.status === 403 || response.redirected )
        return 403;
    else if( !response.ok )
        throw response;

    return await response.json();
}