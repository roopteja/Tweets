#Get tweets from twitter api by keywords, location, twitter account numbers
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream

#Variables that contains the user credentials to access Twitter API 
access_token = "access token"
access_token_secret = "access token secret"
consumer_key = "consumer key"
consumer_secret = "consumer secret"


#This is a basic listener that just prints received tweets to stdout.
class StdOutListener(StreamListener):

    def on_data(self, data):
        print (data)
        with open('filename.json','a') as tf:
            tf.write(data)
        return True

    def on_error(self, status):
        print (status)


if __name__ == '__main__':

    #This handles Twitter authetification and the connection to Twitter Streaming API
    l = StdOutListener()
    auth = OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    stream = Stream(auth, l)

    #This line filter Twitter Streams to capture data by the keywords like: 'python', 'java'
    stream.filter(track=['keyword1', 'keyword2', 'keyword3'])
    #This line filter Twitter Streams to capture data by latitude and longitude box
    stream.filter(locations=[longitude of southwest corner,latitude of southwest corner,longitude of northeast corner,latitude of northeast corner])
    #This line filter Twitter Streams to capture data by the twitter account numbers
    stream.filter(follow=['account1','account2','account3'])