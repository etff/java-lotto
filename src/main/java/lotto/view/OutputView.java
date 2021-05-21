package lotto.view;

import lotto.domain.GameResult;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Prize;

import java.text.DecimalFormat;

/**
 * 결과를 출력한다.
 */
public class OutputView {
    private static final String WINNING_RESULT = "당첨 통계";
    private static final String DASH = "---------";
    private static final String TOTAL_PROFIT = "총 수익률은 ";
    private static final String PROFIT_END = "입니다.";
    private static final String GAME_RESULT = "%d개 일치 (%d원)- %d개";
    private static final String SECOND_PRIZE_PRINT = "%d개 일치, 보너스 볼 일치(%d원)- %d개";
    private static final String UNITS = "개를 구매했습니다.";
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public void showResult(GameResult gameResult, Lotto winningLotto, LottoNumber bonusNumber) {
        System.out.println(WINNING_RESULT);
        System.out.println(DASH);
        for (Prize prize : Prize.values()) {
            showGameResult(prize, gameResult.getResult(winningLotto, bonusNumber));
        }
    }

    public void showInputResult(final Lottos lottos, Money money) {
        int count = money.getLottoCount();
        StringBuilder sb = new StringBuilder();

        sb.append(count).append(UNITS);
        sb.append(System.lineSeparator());
        for (Lotto lotto : lottos.getLottos()) {
            sb.append(lotto);
            sb.append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    public void showProfit(final Money purchasedAmount, final GameResult result) {
        System.out.println(TOTAL_PROFIT + decimalFormat.format(result.getProfit(purchasedAmount)) + PROFIT_END);
    }

    private void showGameResult(Prize prize, GameResult gameResult) {
        if (Prize.MISS == prize) {
            return;
        }
        printSecondPrize(prize, gameResult);
        if (Prize.SECOND != prize) {
            System.out.println(
                    String.format(GAME_RESULT, prize.getMatch(), prize.getAmount(), gameResult.getWinResult(prize))
            );
        }
    }

    private void printSecondPrize(Prize prize, GameResult gameResult) {
        if (Prize.SECOND == prize) {
            System.out.println(String.format(SECOND_PRIZE_PRINT,
                    prize.getMatch(),
                    prize.getAmount(),
                    gameResult.getWinResult(prize)));
        }
    }
}
