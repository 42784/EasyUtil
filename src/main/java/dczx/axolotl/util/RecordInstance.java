package dczx.axolotl.util;

import lombok.Data;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/10/2 14:38
 * <p>
 * 简易的录制音频工具
 */
@Data
public class RecordInstance {

    private String filename;
    private TargetDataLine targetDataLine;
    private AudioInputStream ais;


    public RecordInstance(String filename) {
        this.filename = filename;
    }

    /**
     * 默认的录音方法 录制WAV
     */
    public void startRecording() throws RuntimeException, LineUnavailableException, IOException {
        startRecording(
                48000F, 16,
                1, true, true,
                AudioFileFormat.Type.WAVE
        );
    }

    /**
     * 开始录音
     *
     * @param sampleRate       the number of samples per second
     * @param sampleSizeInBits the number of bits in each sample
     * @param channels         the number of channels (1 for mono, 2 for stereo, and so on)
     * @param signed           indicates whether the data is signed or unsigned
     * @param bigEndian        indicates whether the data for a single sample
     *                         is stored in big-endian byte order (<code>false</code>
     *                         means little-endian)
     * @param type             音频类型
     */
    public void startRecording(
            float sampleRate, int sampleSizeInBits,
            int channels, boolean signed, boolean bigEndian,
            AudioFileFormat.Type type
    ) throws RuntimeException, LineUnavailableException, IOException {
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            throw new RuntimeException("Line not supported");
        }

        targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
        targetDataLine.open(format);
        targetDataLine.start();

        File wavFile = FileUtil.keepFileExists(filename);
        ais = new AudioInputStream(targetDataLine);
        AudioSystem.write(ais, type, wavFile);

    }

    /**
     * 停止录音
     */
    public void stopRecording() throws IOException {
        targetDataLine.stop();
        targetDataLine.close();
        ais.close();
    }

/*    @SneakyThrows
    public static void record() {
        new Thread(RecordService::startRecording).start();
        Thread.sleep(RECORD_TIME);
        RecordService.stopRecording();
    }*/

}
