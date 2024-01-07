/* eslint-disable prettier/prettier */
import {NativeModules} from 'react-native';
const {MultiCDN} = NativeModules;

interface MultiCDNInterface {
  initPilot(token: string): void;
  generateUrl: (originUrl: string, propertyId: string) => string;
}

export default MultiCDN as MultiCDNInterface;
