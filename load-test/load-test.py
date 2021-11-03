import time
from locust import HttpUser, task, between

class ShortenedUrl(HttpUser):
    wait_time = between(1, 5)

    @task
    def hello_world(self):
        self.client.post("/shortened-url/url", json={"originalUrl":"https://dev.mysql.com/doc/refman/8.0/en/create-user.html#:~:text=To%20use%20CREATE%20USER%20%2C%20you,As%20of%20MySQL%208.0."})
