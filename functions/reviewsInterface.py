from classes.review import Review

__REVIEWS_SOURCES = []


def updateReviewsCollection(lastServerReviewsDate):
    """Function gets last server's review's date
        and saves to server all reviews which were
        posted after that date on sources"""

    newReviews = []    #contains reviews from sources which are not on the server yet

    for source in __REVIEWS_SOURCES:
        newReviews.extend(__getListOfNewReviewsFromSource(source))

    newReviews.sort(key = __sortByDate)

    __saveReviews(newReviews)



def __getListOfNewReviewsFromSource(source):
    return

def __sortByDate(news):
    return news.date

def __saveReviews(newReviews):
    return


def updateReviewsApp(lastAppReviewsDate, appSource):
    """Function gets last App review date
        and send relevant review to the client """

    relevantReview = __loadReview(lastAppReviewsDate, appSource) #uploading relevant news from server DB

    for review in relevantReview:
        if(review.date >= lastAppReviewsDate):
            relevantReview.remove(review)

    sendReviewToApp(relevantReview)


def __loadReview(lastAppNewsDate, appSource):
    """Retrieves 15 last reviews from DB with app's source
    """
    reviews = []
    return reviews


def __sendReviewToApp(relevantReview):
    """Send relevant reviews to app's"""
    return