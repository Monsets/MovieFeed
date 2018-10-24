from classes.review import Review

__REVIEWS_SOURCES = []


def updateUnpubReviews(ID,lastServerReviewsDate):
    """Function gets moderator ID and last server's review date
        and return unpublished reviews for the checking"""

    newReviews = []    #contains reviews from server which are unpublished yet

    newReviews.extend(__getListOfNewReviewsFromSerwer(lastServerReviewsDate))

    newReviews.sort(key = __sortByDate)

    return newReviews



def __getListOfNewReviewsFromSerwer(lastServerReviewsDate):

    newReviews = []

    for review in __REVIEWS_SOURCES:
        if(review.date >= lastServerReviewsDate):
            newReviews.append(review)

    return newReviews


def __sortByDate(news):
    return news.date

