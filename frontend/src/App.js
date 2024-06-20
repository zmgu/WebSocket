import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import './App.css';

function App() {

    const [stompClient, setStompClient] = useState(null);
    const [connected, setConnected] = useState(false);
    const [message, setMessage] = useState('');
    const [messages, setMessages] = useState([]);

    useEffect(() => {

        const url = 'http://localhost:8080/stomp/ws';
        const token = 'jwqkrlejl2j14kljekwlqfkasd21wqeqwewnfkl'

        const client = new Client({
            webSocketFactory: () => new SockJS(url),  // 백엔드 웹소켓 엔드포인트
            connectHeaders: {   // 웹소켓 헤더 인증정보 추가
                Authorization: `Bearer ${token}`
            },
            onConnect: () => {
              setConnected(true);
              client.subscribe('/stomp/sub/received', (contents) => {   // 컨트롤러 @SendTo의 엔드포인트
                    const receivedMessage = JSON.parse(contents.body);
                    showMessage(receivedMessage.content);
              });
            },
            onStompError: (frame) => {
              console.error('Broker reported error: ' + frame.headers['message']);
              console.error('Additional details: ' + frame.body);
            }
        });

        setStompClient(client);

        return () => {
            if (client.active) {
                client.deactivate();
            }
        };
    }, []);

    const connect = () => {
        if (stompClient) {
            stompClient.activate();
        }
    };

    const disconnect = () => {
        if (stompClient) {
            stompClient.deactivate();
        }
        setConnected(false);
        console.log("Disconnected");
    };

    const sendMessage = () => {
        if (stompClient && message) {
            stompClient.publish({
                destination: "/stomp/pub/send",  // 컨트롤러 @MessageMapping의 엔드포인트
                body: JSON.stringify({ message })
            });
        }
    };

    const showMessage = (message) => {
        setMessages(prevMessages => [...prevMessages, message]);
    };

    return (
      <div className="App">
          <div>
              <button id="connect" onClick={connect} disabled={connected}>Connect</button>
              <button id="disconnect" onClick={disconnect} disabled={!connected}>Disconnect</button>
          </div>
          {connected && (
              <div id="conversation">
                  <input
                      type="text"
                      id="name"
                      value={message}
                      onChange={(e) => setMessage(e.target.value)}
                      placeholder="메시지를 입력하세요."
                  />
                  <button id="send" onClick={sendMessage}>Send</button>
              </div>
          )}
          <table id="greetings">
              <tbody>
                  {messages.map((data, index) => (
                      <tr key={index}><td>{data}</td></tr>
                  ))}
              </tbody>
          </table>
      </div>
  );
}

export default App;
