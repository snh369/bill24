import { Environment } from "./environment.type";

export const environment: Environment = {
    production: false,
    baseAPI: {
        server: 'http://localhost:4141/api/v1',
        mapKey: '',
        googleAuthClientId: '',
        webSocketUrl: ''
    }
};