from spider import Spider
from domain import *
from general import *
import urllib

mp3 = set()
PROJECT_NAME = 'newsonair'
HOMEPAGE = 'http://www.newsonair.com/'
DOMAIN_NAME = get_domain_name(HOMEPAGE)
QUEUE_FILE = PROJECT_NAME + '/queue.txt'
Spider(PROJECT_NAME, HOMEPAGE, DOMAIN_NAME)

def extractmp3():
    q_links = file_to_set(QUEUE_FILE)
    f = open('mp3.txt','w')
    for link in q_links:
        if '.mp3' in link and ('English' in link):
             f.write(link+"\n")

    f.close()
extractmp3()
print('Retriving news..')
a=0
print('Retrived news..')
