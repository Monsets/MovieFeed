import feedparser
import requests
import re

from classes.news import News

__NEWS_SOURCES = ['https://st.kp.yandex.net/rss/news_feed.rss']


def updateNewsCollection(lastServerNewsDate):
    """Function gets last server's news date
        and saves to server all news which were
        posted after that date on sources"""

    newNews = []    #contains news from sources which are not on the server yet

    for source in __NEWS_SOURCES:
        newNews.extend(__getListOfNewNewsFromSource(source, lastServerNewsDate))

    newNews.sort(key = __sortByDate)

    __saveNews(newNews)

def __getListOfNewNewsFromSource(source, lastServerNewsDate):

    feed = feedparser.parse(source)['entries']
    news = []

    for item in feed:
        title = item['title']
        date = item['published_parsed']
        text = item['summary']
        link = item['link']
        picture = __getImageFromSite(link)
        news.append(News(picture, date, link, title, text))

    return news

def __getImageFromSite(site):
    """This function gets image for news from site"""
    response = requests.get(site)
    imgLink = re.search(r'https://avatars.mds.yandex.net/get-kinopoisk-post-img/[\w/-]+', response.text)

    return imgLink.group(0)

def __sortByDate(news):
    return news.date

def __saveNews(newNews):
    return


def updateNewsApp(lastAppNewsDate, appSource):
    """Function gets last App news date
        and send relevant news to the client """

    relevantNews = __loadNews(lastAppNewsDate, appSource) #uploading relevant news from server DB

    for news in relevantNews:
        if(news.date >= lastAppNewsDate):
            relevantNews.remove(news)

    __sendNewsToApp(relevantNews)


def __loadNews(lastAppNewsDate, appSource):
    """Retrieves from DB 15 last news from with app's source
    """
    news = []
    return news


def __sendNewsToApp(relevantNews):
    """Send relevant news to app's"""
    return