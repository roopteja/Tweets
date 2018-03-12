import datetime
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream

consumer_key = "hx8dtAl9owiE7Qp0d8Q5eJA9S"
consumer_secret = "UNXSZqsqL5DZGJZlF89qHuv32zlzJWYmEKIEWpP6DjzeinVRhz"
access_token = "3984860007-X85dVBUnxnt92pswoX3Ngk5uwXlQpUToHFD4uls"
access_token_secret = "kHYDQrNPLBXTaFX1LBrEvhurNcw7jCp4eOG90isKT7jtd"


class StdOutListener(StreamListener):

    def on_data(self, data):
        print(data)
        return True

    def on_error(self, status):
        print(status)

if __name__ == '__main__':
    l = StdOutListener()
    auth = OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    stream = Stream(auth, l)
    stream.filter(locations=[-73.934877,40.800904,-73.919728,40.830720])
    #stream.filter(follow=['42640432','2869012466','531603768','50706690','52272942','42744320','51149096'])
