package net.cefaratez.usercloud.music.chain

import org.springframework.stereotype.Component
import ratpack.groovy.handling.GroovyChainAction
import ratpack.hystrix.HystrixMetricsEventStreamHandler

@Component
class HystrixStreamChainAction extends GroovyChainAction {

    @Override
    void execute() throws Exception {
        get('admin/hystrix.stream', new HystrixMetricsEventStreamHandler())
    }
}
