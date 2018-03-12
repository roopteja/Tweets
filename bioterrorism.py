from tweepy.streaming import StreamListener
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
        with open('D:/Study/Knoesis/bioterrorism/bioterrorism.json','a') as tf:
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

    #This line filter Twitter Streams to capture data by the keywords: 'python', 'javascript', 'ruby'
    stream.filter(track=['tularemia', 'biosafety', 'amerithrax', 'biological weapon', 'bioterrorism', 'biological warfare', 'bioterrorist', 'botulism', 'biosecurity', 'plague', 'bacillus anthracis', 'anthrax letter', 'anthrax', 'smallpox'])