package producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author huanyao
 * @version 1.0
 * @ClassName SyncProducer.java
 * @Description 消息生产者-同步
 * @date 2021/4/7 4:56 下午
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //初始化消息生产者
        DefaultMQProducer producer = new
                DefaultMQProducer("please_rename_unique_group_name");
        // 指定 name server 地址.
        producer.setNamesrvAddr("localhost:9876");
        //调用实例.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //创建消息实例, 指定 topic, tag 和 消息体 .
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //发送消息给brokers
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //销毁生产者实例
        producer.shutdown();
    }
}
