const solutions = {
    getByTaskId: solutionsGetByTaskId
};

const executorRating = {
    getByUserIds: executorRatingGetByUserIds
};

window.API = {
    solutions,
    executorRating
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