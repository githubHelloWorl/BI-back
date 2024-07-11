package com.yupi.springbootinit.manager;

import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.model.DevChatRequest;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class AIManager {

    @Resource
    private YuCongMingClient yuCongMingClient;

    /**
     * AI 对话
     * @param message
     * @return
     */
    public String doChat(long modelId, String message){
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(modelId);
        devChatRequest.setMessage(message);
//        BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
//        System.out.println("AI 分析:");
//        System.out.println(response);
//        if(response == null){
//            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"AI 响应异常");
//        }
//
//        return response.getData().getContent();

        // 折线图
        String myDefault = "【【【\n" +
                "{\"tooltip\":{\"trigger\":\"axis\"},\"xAxis\":{\"type\":\"category\",\"data\":[\"1号\",\"2号\",\"3号\"]},\"yAxis\":{\"type\":\"value\"},\"series\":[{\"data\":[50,20,30],\"type\":\"line\",\"smooth\":true}]}" +
                "【【【\n" +
                "从1号到3号，用户数呈现先下降后上升的趋势。1号用户数为50，2号下降至20，3号回升至30。这种波动可能由多种因素引起，如市场活动、用户反馈或其他外部事件。2号用户数的下降可能表明存在用户流失或参与度下降的问题，而3号的增长则可能意味着采取了有效的用户吸引策略或市场条件有所改善。尽管3号的用户数没有达到1号的水平，但上升趋势表明了积极的用户增长潜力。建议进一步分析用户行为数据，以确定增长和下降的具体原因，并制定相应的策略来优化用户体验和提高用户留存率。\n";

        // 柱状图
        String myGird = "【【【\n" +
                "{\"grid\":{\"containLabel\":true},\"tooltip\":{\"trigger\":\"axis\",\"axisPointer\":{\"type\":\"shadow\"}},\"xAxis\":{\"type\":\"category\",\"data\":[\"1号\",\"2号\",\"3号\",\"4号\"],\"axisLabel\":{\"showMinLabel\":false}},\"yAxis\":{\"type\":\"value\"},\"series\":[{\"data\":[15,15,14,13],\"type\":\"bar\",\"barWidth\":\"60%\",\"label\":{\"show\":true,\"position\":\"right\"}}]}\n" +
                "【【【\n" +
                "从1号到4号，用户数显示出轻微的下降趋势。具体来看，1号和2号的用户数保持稳定，均为15。然而，从3号开始，用户数开始下降，3号的用户数为14，比前一天减少了1。到了4号，用户数进一步下降到13，比3号又减少了1。这种连续的下降可能表明用户对网站的新鲜感正在减退，或者可能存在其他导致用户流失的因素。为了更深入地了解用户流失的原因，建议进行进一步的用户行为分析，包括但不限于用户留存率、活跃度以及用户反馈。此外，可以考虑进行用户满意度调查，以识别可能的问题点并采取相应的改进措施。同时，监测同期的市场活动或竞争对手动态，以评估它们是否对用户数的变化有影响。\n";

        // 堆叠图
        String myCross = "【【【\n" +
                "{\"tooltip\":{\"trigger\":\"axis\",\"axisPointer\":{\"type\":\"cross\"}},\"xAxis\":{\"type\":\"category\",\"data\":[\"1号\",\"2号\",\"3号\",\"4号\"]},\"yAxis\":{\"type\":\"value\"},\"series\":[{\"name\":\"用户数\",\"type\":\"bar\",\"stack\":\"总量\",\"data\":[15,15,14,13]}]}\n" +
                "【【【\n" +
                "从1号到4号，网站用户数呈现轻微下降趋势。1号和2号的用户数保持在15，但从3号开始用户数减少到14，4号进一步减少到13。这种趋势表明，尽管在最初两天用户数保持稳定，但随后几天用户数有所下降。建议进一步分析用户行为数据，以了解用户流失的原因，并考虑采取相应的措施来提高用户留存率和活跃度。\n";

        // 饼图
        String myPie = "【【【\n" +
                "{\"tooltip\":{\"trigger\":\"item\",\"formatter\":\"{a} <br/>{b} : {c} ({d}%)\"},\"legend\":{\"orient\":\"vertical\",\"left\":\"left\"},\"series\":[{\"name\":\"用户数\",\"type\":\"pie\",\"radius\":\"50%\",\"data\":[{\"value\":15,\"name\":\"1号\"},{\"value\":15,\"name\":\"2号\"},{\"value\":14,\"name\":\"3号\"},{\"value\":13,\"name\":\"4号\"}],\"emphasis\":{\"itemStyle\":{\"shadowBlur\":10,\"shadowOffsetX\":0,\"shadowColor\":\"rgba(0, 0, 0, 0.5)\"}}}]}\n" +
                "【【【\n" +
                "饼图展示了每天用户数占整个观察期间用户总数的比例。根据数据，1号和2号的用户数相同，占比最大，而3号和4号的用户数略有下降。饼图并不直接展示用户数随时间的变化趋势，但可以看出每天用户数的相对大小。如果需要更直观地展示用户数随时间的变化，建议使用柱状图或折线图。然而，就当前数据而言，饼图有效地揭示了不同日期用户数的分布情况。\n";

        // 雷达图
        String myRaday = "【【【\n" +
                "{\"radar\":{\"indicator\":[{\"name\":\"1号\",\"max\":20},{\"name\":\"2号\",\"max\":20},{\"name\":\"3号\",\"max\":20},{\"name\":\"4号\",\"max\":20}]},\"tooltip\":{},\"series\":[{\"type\":\"radar\",\"data\":[{\"value\":[15,15,14,13],\"name\":\"用户数\"}]}]}\n" +
                "【【【\n" +
                "雷达图展示了从1号到4号用户数的相对变化。根据数据，1号和2号的用户数均为15，处于较高水平，而3号和4号的用户数逐渐下降，分别为14和13。这种下降趋势可能表明用户对网站的兴趣或参与度在逐渐降低。\n" +
                "雷达图的形状清晰地揭示了用户数在不同日期的表现，显示出一种逐渐减弱的趋势。这种趋势可能受到多种因素的影响，如市场环境的变化、用户需求的转移、网站内容的吸引力等。\n" +
                "为了应对这种下降趋势，建议深入分析用户行为数据，了解用户流失的具体原因。同时，考虑通过改进网站内容、优化用户体验、加强用户互动等方式来提升用户满意度和参与度。此外，监测市场活动和竞争对手动态也是必要的，以便及时调整策略，应对市场变化。\n";

        String ans;
        if(message.contains("请使用柱状图")){
            System.out.println("柱状图");
            ans  = myGird;
        }else if(message.contains("请使用堆叠图")){
            System.out.println("堆叠图");
            ans = myCross;
        }else if(message.contains("请使用饼图")){
            System.out.println("饼图");
            ans = myPie;
        }else if(message.contains("请使用雷达图")){
            System.out.println("雷达图");
            ans = myRaday;
        }else{
            System.out.println("折线图");
            ans = myDefault;
        }

        // TODO 获得结果
        return ans;
    }
}
