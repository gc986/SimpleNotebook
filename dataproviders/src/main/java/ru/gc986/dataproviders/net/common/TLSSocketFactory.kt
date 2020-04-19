package ru.gc986.dataproviders.net.common

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


class TLSSocketFactory : SSLSocketFactory() {

    companion object {
        private val TLSv_1_2 = "TLSv1.2"
        private val ENABLED_PROTOCOLS = arrayOf(TLSv_1_2)
    }

    override fun getDefaultCipherSuites(): Array<String> = sslSocketFactory.defaultCipherSuites

    override fun getSupportedCipherSuites(): Array<String>  = sslSocketFactory.supportedCipherSuites

    //The real thing
    private val sslSocketFactory: SSLSocketFactory

    init {
        try {
            val sslContext = SSLContext.getInstance(TLSv_1_2)
            sslContext.init(null, null, null) //<= use defaults
            this.sslSocketFactory = sslContext.getSocketFactory()
        } catch (e: Exception) {
            throw RuntimeException("Could not initialize TLSv1_2OnlySSLSocketFactory.")
        }

    }

    @Throws(IOException::class)
    override fun createSocket(s: Socket, host: String, port: Int, autoClose: Boolean): Socket {
        val socket = sslSocketFactory.createSocket(s, host, port, autoClose) as SSLSocket
        socket.setEnabledProtocols(ENABLED_PROTOCOLS)
        return socket
    }

    @Throws(IOException::class)
    override fun createSocket(host: String, port: Int): Socket {
        val socket = sslSocketFactory.createSocket(host, port) as SSLSocket
        socket.setEnabledProtocols(ENABLED_PROTOCOLS)
        return socket
    }

    @Throws(IOException::class)
    override fun createSocket(host: String, port: Int, localHost: InetAddress, localPort: Int): Socket {
        val socket = sslSocketFactory.createSocket(host, port, localHost, localPort) as SSLSocket
        socket.setEnabledProtocols(ENABLED_PROTOCOLS)
        return socket
    }

    @Throws(IOException::class)
    override fun createSocket(host: InetAddress, port: Int): Socket {
        val socket = sslSocketFactory.createSocket(host, port) as SSLSocket
        socket.setEnabledProtocols(ENABLED_PROTOCOLS)
        return socket
    }

    @Throws(IOException::class)
    override fun createSocket(address: InetAddress, port: Int, localAddress: InetAddress, localPort: Int): Socket {
        val socket = sslSocketFactory.createSocket(address, port, localAddress, localPort) as SSLSocket
        socket.setEnabledProtocols(ENABLED_PROTOCOLS)
        return socket
    }

    @Throws(IOException::class)
    override fun createSocket(): Socket {
        val socket = sslSocketFactory.createSocket() as SSLSocket
        socket.setEnabledProtocols(ENABLED_PROTOCOLS)
        return socket
    }

}