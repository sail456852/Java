package kafka.basic;

import kafka.ProducerProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * 1.you have to create the topic using CLI or WEB UI first, before this producer can successfully produce the message
 * 2.If the topic is not pre-exist, this program won't work
 * Remote one free broker, web ui for that more broker
 * The broker WEB UI url: <a href="https://console.upstash.com/kafka/514bfd93-8a06-4841-8cd4-0b4064f310a2/030665d9-9278-4e36-a9c0-3d64921b088d"/>
 * The followings are from the UI,which means event(message/record) sent to the topic succeeded
 * Messages
 * 1
 * Partitions
 * 1
 * Current Storage
 * 125B
 */
public class ProducerClient {
    public static void main(String[] args) throws Exception {
        var props = ProducerProperties.getBasicProperties();

        try (var producer = new KafkaProducer<String, String>(props)) {
            String topic = "todolist_topic";
            String message = "this is a message to be sent to the todolist_topic topic with a callback method";
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
            producer.send(record, (metadata, exception) -> {
                // this callback is actually the acknowledgement from brokers,that the event or message is received, for confirmation purpose
                if (exception == null) {
                    System.out.printf("%s : %s : %d : %s", metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
                } else {
                    System.err.println(exception.getMessage());
                }
            });
        }
    }
}
