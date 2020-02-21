package com.fpt.parser.logtype.click

class InfoAllColumn {
  case class InfoColumn(nameColumn: String, typeValues: String)

  val timestamp = InfoColumn("@timestamp", "string")
  val version = InfoColumn("@version", "string")
  val bodyBytesSent = InfoColumn("body_bytes_sent", "int")
  val bytesSent = InfoColumn("bytes_sent", "int")
  val host = InfoColumn("host", "string")
  val hostname = InfoColumn("hostname", "string")
  val httpUserAgent = InfoColumn("http_user_agent", "string")
  val isp = InfoColumn("isp", "string")
  val receiveTime = InfoColumn("receive_time", "string")
  val remoteAddr = InfoColumn("remote_addr", "string")
  val requestLength = InfoColumn("request_length", "int")
  val requestMethod = InfoColumn("request_method", "string")
  val requestTime = InfoColumn("request_time", "float")
  val requestUri = InfoColumn("request_uri", "string")
  val serverPort = InfoColumn("server_port", "string")
  val serverProtocol = InfoColumn("server_protocol", "string")
  val services = InfoColumn("services", "string")
  val sourceHost = InfoColumn("source_host", "string")
  val status = InfoColumn("status", "int")
  val uri = InfoColumn("uri", "string")

  val listColumn = List(timestamp, version, bodyBytesSent, bytesSent, host, hostname, httpUserAgent,
    isp, receiveTime, remoteAddr, requestLength, requestMethod, requestTime, requestUri, serverPort,
    serverProtocol, services, sourceHost, status, uri
  )


}
