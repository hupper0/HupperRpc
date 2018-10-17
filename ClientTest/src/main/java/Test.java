import client.RpcClient;
import client.ServiceDiscovery;
import com.hupper.rpc.service.HelloService;

/**
 * @author hupper
 * @date 2018/9/29
 */
public class Test {


    public static void main(String[] args) throws InterruptedException {


        ServiceDiscovery serviceDiscovery = new ServiceDiscovery("127.0.0.1:2181");
        final RpcClient rpcClient = new RpcClient(serviceDiscovery);

        final HelloService syncClient = rpcClient.create(HelloService.class);

        syncClient.hello("lvhongpeng nice~~!!!!");
    }
}
