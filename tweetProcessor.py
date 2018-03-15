import re
import pandas
import string
from nltk.corpus import stopwords

punctuation = list(string.punctuation)
numbers = list(string.digits)
stop = stopwords.words('english') + punctuation + numbers + ['rt', 'via', 'a', 'an', 'the', 'is', 'of', 'to', 'on', 'for', 'in', 'at', 'by', 'and', 'it', 'be', 'so', 'this', 'that', 'or', 'you', 'will', 'we', 'are', 'your', 'be', 'how', 'what', 'can', 'from', 'as', 'zika', 'virus', 'about', 'like', 'but', 'my', 'dont', 'more', 'all', 'now', 'not', 'there', 'if', 'just', "don't", "there's", 'cc', 'u', 'may']

def preprocess_tweet(tweet):
    tweet = str(tweet).lower()
    tweet = re.sub(r'\w+:\/{2}[\d\w-]+(\.[\d\w-]+)*(?:(?:\/[^\s/]*))*', '', tweet)
    # additional preprocessing
    tweet = tweet.replace("\n", "").replace(" https","").replace("http","").replace(":","").replace("#"," ").replace("-"," ")
    # remove retweet indicator
    tweet = re.sub(r'\brt\b', '', tweet)
    # remove screen handles
    tweet = re.sub(r"@\w+[\s]*", '', tweet)
    # remove non-ascii characters
    tweet = ''.join([i if ord(i) < 128 else ' ' for i in tweet])
    # remove stop words
    tweet = [word for word in tweet.split(" ") if word not in stop]
    tweet = " ".join(tweet)
    # remove multiple spaces
    tweet = re.sub(r'(\s+)', ' ', tweet)
    return tweet.strip()

if __name__ == "__main__":
    data = pandas.read_csv("input.csv", sep=",", quotechar = '"', usecols = [0], header=None)
	# if the file contains any special characters use encoding
	# data = pandas.read_csv("input.csv", sep=",", quotechar = '"', usecols = [0], header=None, encoding = "ISO-8859-1")
    data.columns = ["Tweets"]
    tweets = data['Tweets'].values
    clean_tweets = open("output.csv",'w')
    for i in range(0,len(tweets)):
        tweet = preprocess_tweet(data.Tweets[i])
        clean_tweets.write('"'+str(tweet)+'"')
        clean_tweets.write("\n")
