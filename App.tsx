/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, {useRef} from 'react';
import {
  SafeAreaView,
  StatusBar,
  StyleSheet,
  useColorScheme,
} from 'react-native';
// @ts-ignore
import Video, {VideoRef} from 'react-native-video';
import MultiCDN from './modules/MultiCDN';

const SECURITY_TOKEN = 'a73dade4e0e923d62b427e746369a7';
const PROPERTY_ID = 'DEMO_DASH';
const ORIGIN_URL =
  'https://live-on-v2-akm.akamaized.net/manifest/test_live/master.m3u8';

MultiCDN.initPilot(SECURITY_TOKEN);

function App(): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';
  const videoRef = useRef<VideoRef>(null);

  const url = MultiCDN.generateUrl(ORIGIN_URL, PROPERTY_ID);

  console.log('url ', url);

  return (
    <SafeAreaView style={{flex: 1, flexDirection: 'row'}}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <Video
        source={{
          uri: MultiCDN.generateUrl(ORIGIN_URL, PROPERTY_ID),
          type: 'm3u8',
        }}
        ref={videoRef} // Store reference
        key="video-player"
        // onBuffer={this.onBuffer} // Callback when remote video is buffering
        // onError={this.videoError} // Callback when video cannot be loaded
        // onProgress={this.onProgress}
        // onLoadStart={this.onLoad}
        controls={true}
        resizeMode="contain"
        // fullscreen={true}
        style={styles.backgroundVideo}
      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  backgroundVideo: {
    position: 'absolute',
    top: 0,
    left: 0,
    bottom: 0,
    right: 0,
  },
  btnAnalytic: {
    justifyContent: 'center',
    alignItems: 'center',
    width: 120,
    height: 30,
    borderRadius: 2,
    backgroundColor: '#2bc4e3',
    borderWidth: 1,
    borderColor: 'rgba(0, 176, 173, 0.5)',
  },
});

export default App;
