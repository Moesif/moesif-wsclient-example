package com.moesif.sdk.sample.wsclient.scala

import com.moesif.sdk.sample.wsclient.{CreateEvent, MoesifClient, MoesifResponse}

/**
 * This is the WS Client Scala example to send a sample event to Moesif
 */
object MoesifWsClientScalaExample {

  /**
   * This is the main function
   * @param args Moesif Application ID, (optional URL)
   */
  def main(args: Array[String]): Unit ={
    if (args.length == 0 || args(0) == "<Your-Moesif-Application-ID>") {
      println("Your Moesif Application ID is missing")
      return
    }
    val moesifApplicationID: String = args(0)

    //To override URL, add alternate URL in args
    var urlVar: String = "https://api.moesif.net/v1/events"
    if (args.length == 2) urlVar = args(1)


    val event: String = buildSampleMoesifEvent()
    val misterClient: MoesifClient = new MoesifClient
    val resp: MoesifResponse = misterClient.sendToMoesif(urlVar, event, moesifApplicationID)

    println("Response status code: " + resp.statusCode)
    if (resp.body.length > 1)
      println("Response body: " + resp.body)
    if (resp.statusCode >= 400)
      println("There was an error in sending the event to Moesif")
    else
      println("Event successfully sent to Moesif")
  }

  /**
   * Builds the sample Moesif event using CreateEvent class
   * @return Sample event body
   */
  def buildSampleMoesifEvent(): String = {
    val targetUri = "http://localhost:5000/todo/api/v1.0/tasks"
    val verb = "POST"
    val host = "localhost:5000"
    val userAgent = "insomnia/7.1.1"
    val contentType = "application/json"
    val ipAddress = "127.0.0.1"

    val m = new CreateEvent
    val moesifEventBody: String = m.createBody(targetUri, verb, host, userAgent, contentType, ipAddress)
    moesifEventBody
  }
}