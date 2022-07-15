# Moesif WS Client Example

WS is a library used in code to call REST APIs.

[Moesif](https://www.moesif.com) is an API analytics and monitoring
platform. MoesifWsClientExample is a sample program which makes and
sends a sample API to Moesif.

## How to run this example
Send single event to Moesif (Java):
```bash
sbt "runMain com.moesif.sdk.sample.wsclient.MoesifWsClientExample <Your-Moesif-Application-ID>"
```

Send single event to Moesif (Scala):
```bash
sbt "runMain com.moesif.sdk.sample.wsclient.scala.MoesifWsClientScalaExample <Your-Moesif-Application-ID>"
```

Send a batch of events (Java):
```bash
sbt "runMain com.moesif.sdk.sample.wsclient.MoesifWsClientBatchExample <Number-of-events> <Your-Moesif-Application-ID>"
```

Your Moesif Collector Application Id can be found in the
[_Moesif Portal_](https://www.moesif.com/). After signing up for a
Moesif account, your Moesif Application Id will be displayed during
the onboarding steps.

You can always find your Moesif Collector Application Id at any time
by logging into the [_Moesif Portal_](https://www.moesif.com/), clicking
on the bottom left user profile, and then clicking _API Keys_.  
\
After running the example, you can now view events from Moesif Event Log

### How to run unit tests
```bash
sbt test
```
## Tested versions

Moesif has validated MoesifWsClientExample against the following combinations:

| Scala         | Play WS          |
|---------------|------------------|
| Scala 2.11.12 | 2.7.9<br/>2.8.16 |
| Scala 2.13.8  | 2.7.9<br/>2.8.16 |
| Scala 2.12.16 | 2.7.9<br/>2.8.16 |


### Future Updates
* Incorporate examples of building Moesif event using jackson and gson
* Add example for sending events in batch