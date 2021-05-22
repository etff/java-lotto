package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {
    private List<LottoNumber> lottoNumbers = Arrays.asList(
            new LottoNumber(1), new LottoNumber(2),
            new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(6)
    );
    private List<LottoNumber> lottoNumbers2 = Arrays.asList(
            new LottoNumber(1), new LottoNumber(2),
            new LottoNumber(3), new LottoNumber(9),
            new LottoNumber(5), new LottoNumber(7)
    );
    private List<Lotto> lotto = new ArrayList<>();
    private List<Lotto> lotto2 = new ArrayList<>();

    @BeforeEach
    void setUp() {
        lotto.add(new Lotto(lottoNumbers));
        lotto2.add(new Lotto(lottoNumbers2));
    }

    @DisplayName("로또 목록을 생성할 수 있다.")
    @Test
    void addLotto() {
        Lottos lottos = new Lottos(lotto, lotto2);

        assertThat(lottos.getSize()).isEqualTo(2);
    }
}
